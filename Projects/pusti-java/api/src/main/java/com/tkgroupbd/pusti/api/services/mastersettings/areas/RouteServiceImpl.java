package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.RouteSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RouteRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.hrm.employees.EmployeeRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.RouteRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.RouteSalesOfficerRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.ZoneRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DistributorRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.SalesOfficerRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private RouteRepository routeRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private ZoneRepository zoneRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistributorRepository distributorRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private EmployeeRepository employeeRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    SalesOfficerRepository salesOfficerRepository;

    @Override
    public MessageResponse saveRoute(RouteRequest routeRequest) {
        try {
            Route route = new Route();

            route.setRouteName(routeRequest.getRouteName().trim().toUpperCase());
            route.setLongitude(routeRequest.getLongitude());
            route.setLatitude(routeRequest.getLatitude());
            route.setContributionPercentage(routeRequest.getContributionPercentage());
            route.setZone(routeRequest.getZone());
            route.setDistributor(routeRequest.getDistributor());
            route.setStatus(routeRequest.isStatus());
            route.setRouteSalesOfficerList(routeRequest.getRouteSalesOfficerList());
            routeRepository.save(route);
            return new MessageResponse(Message.SUCCESS_CREATION);

        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }

    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    @Transactional
    public Route findRouteById(int routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            return optionalRoute.get();
        } else {
            throw new EntityNotFoundException("Route not found with id: " + routeId);
        }
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public MessageResponse updateRoute(int routeId, RouteRequest routeRequest) {
        Optional<Route> existingRouteOptional = routeRepository.findById(routeId);
        if (!existingRouteOptional.isPresent()) {
            return new MessageResponse(Message.FAILED_UPDATE);
        } else {
            Route route = existingRouteOptional.get();
            route.setRouteName(routeRequest.getRouteName().trim().toUpperCase());
            route.setLongitude(routeRequest.getLongitude());
            route.setLatitude(routeRequest.getLatitude());
            route.setContributionPercentage(routeRequest.getContributionPercentage());
            route.setZone(routeRequest.getZone());
            route.setDistributor(routeRequest.getDistributor());
            route.setStatus(routeRequest.isStatus());
            route.setRouteSalesOfficerList(routeRequest.getRouteSalesOfficerList());
            routeRepository.save(route);
            return new MessageResponse(Message.SUCCESS_UPDATE);
        }
    }

    @Override
    public MessageResponse updateRouteLatLong(int routeId, RouteRequest routeRequest) {
        Optional<Route> existingRouteOptional = routeRepository.findById(routeId);

        if (!existingRouteOptional.isPresent()) {
            return new MessageResponse(Message.FAILED_UPDATE);
        } else {
            Route existingRoute = existingRouteOptional.get();
            existingRoute.setLongitude(routeRequest.getLongitude());
            existingRoute.setLatitude(routeRequest.getLatitude());
            routeRepository.save(existingRoute);
            return new MessageResponse(Message.SUCCESS_UPDATE);
        }
    }

    @Override
    public Optional<Route> updateRouteStatus(int routeId, RouteRequest routeRequest) {
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isEmpty()) {
            throw new ResourceNotFoundException("Route", "routeId", routeId);
        } else {
            route.get().setStatus(routeRequest.isStatus());
            routeRepository.save(route.get());
        }
        return route;
    }

    @Override
    public List<Route> getByRouteName(String routeName) {
        return routeRepository.findAllByRouteNameContainingIgnoreCase(routeName);
    }

    public List<Route> getAllRoutesByDbNameOrZoneNameOrRegionNameOrDivisionName(String zoneName) {
        List<Route> routeList = routeRepository.findAllDbNameOrZoneNameOrRegionNameOrDivisionName(zoneName.toUpperCase().trim());
        Collections.sort(routeList, Comparator.comparing(Route::getRouteName));
        return routeList;
    }

    @Override
    public Page<Route> findRoutesByPagination(int offset, int pageSize) {
        Page<Route> routes = routeRepository.findAll(PageRequest.of(offset, pageSize));
        return routes;
    }


    public MessageResponse uploadCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 11) {
                    String routeName = data[0].toLowerCase().trim();
                    Route route = routeRepository.findByRouteName(routeName)
                            .orElseGet(() -> createNewRoute(data));

                    RouteSalesOfficer routeSalesOfficer = new RouteSalesOfficer();
                    routeSalesOfficer.setVisitingFrequency(Integer.parseInt(data[6]));
                    routeSalesOfficer.setPreferredDays(data[7]);
                    routeSalesOfficer.setAddPermit(Boolean.parseBoolean(data[8]));
                    routeSalesOfficer.setEditPermit(Boolean.parseBoolean(data[9]));

                    int salesOfficerId = Integer.parseInt(data[10]);
                    Optional<Employee> employee = employeeRepository.findById(salesOfficerId);
                    employee.ifPresent(routeSalesOfficer::setEmployee);

                    route.getRouteSalesOfficerList().add(routeSalesOfficer);

                    routeRepository.save(route);
                }
            });

            return new MessageResponse(Message.SUCCESS_CSV_UPLOAD);
        } catch (Exception e) {
            return new MessageResponse("Error while processing CSV file: " + e.getMessage());
        }
    }

    private Route createNewRoute(String[] data) {
        Route route = new Route();
        route.setRouteName(data[0].toLowerCase().trim());
        route.setContributionPercentage(Double.parseDouble(data[1]));
        route.setLatitude(Double.parseDouble(data[2]));
        route.setLongitude(Double.parseDouble(data[3]));
        int zoneId = Integer.parseInt(data[4]);
        int distributorId = Integer.parseInt(data[5]);
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (zone.isPresent() && distributor.isPresent()) {
            route.setZone(zone.get());
            route.setDistributor(distributor.get());
            routeRepository.save(route);
        }
        return route;
    }

    @Override
    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        List<Route> routes = routeRepository.findAll(); // You should have a RouteRepository to fetch data

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Routes Data");

        // Create a header row
        Row headerRow = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();

        headerFont.setColor(IndexedColors.BLUE.getIndex());
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);

        CellStyle aquaCellStyle = workbook.createCellStyle();
        aquaCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        aquaCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font aquaFont = workbook.createFont();
        aquaFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        aquaFont.setBold(true);
        aquaCellStyle.setFont(aquaFont);



        for (int i = 0; i<17; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(getHeaderName(i));
            cell.setCellStyle(headerCellStyle);
        }


        int rowNum = 1;
        for (Route route : routes) {
            Row row = sheet.createRow(rowNum++);

            Cell slCell = row.createCell(0);
            row.createCell(0).setCellValue(rowNum - 1);
            slCell.setCellStyle(aquaCellStyle);

            row.createCell(1).setCellValue(route.getZone().getZoneName());
            row.createCell(2).setCellValue(route.getZone().getRegion().getRegionName());
            row.createCell(3).setCellValue(route.getDistributor().getDistributorId());
            row.createCell(4).setCellValue(route.getDistributor().getName());
            row.createCell(5).setCellValue(route.getDistributor().isHasLiveApp());
            row.createCell(6).setCellValue(route.getUpdatedAt());
            row.createCell(7).setCellValue(route.getRouteId());
            row.createCell(8).setCellValue(route.getRouteName());
            row.createCell(9).setCellValue(route.getContributionPercentage());

            if (route.getRouteSalesOfficerList() != null) {
                row.createCell(10).setCellValue(route.getRouteSalesOfficerList().iterator().next().getVisitingFrequency());
                row.createCell(11).setCellValue(route.getRouteSalesOfficerList().iterator().next().isAddPermit());
                row.createCell(12).setCellValue(route.getRouteSalesOfficerList().iterator().next().isEditPermit());
                row.createCell(13).setCellValue("");
                row.createCell(14).setCellValue(route.getRouteSalesOfficerList().iterator().next().isStatus());

                if (route.getRouteSalesOfficerList().iterator().next().getEmployee() != null) {
                    row.createCell(15).setCellValue(route.getRouteSalesOfficerList().iterator().next().getEmployee().getEmployeeId());
                }else {
                    row.createCell(15).setCellValue("");
                }
                row.createCell(16).setCellValue(route.getRouteSalesOfficerList().iterator().next().getPreferredDays());
            } else {
                row.createCell(10).setCellValue("");
                row.createCell(11).setCellValue("");
                row.createCell(12).setCellValue("");
                row.createCell(13).setCellValue("");
                row.createCell(14).setCellValue("");
                row.createCell(15).setCellValue("");
                row.createCell(16).setCellValue("");
            }
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=routes.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    private String getHeaderName(int index){
        switch (index){
            case 0 : return "SL";
            case 1 : return "Zone Name";
            case 2 : return "Region Name";
            case 3 : return "DB ID";
            case 4 : return "DB Name";
            case 5 : return "App";
            case 6 : return "Update";
            case 7 : return "Route ID";
            case 8 : return "Route Name";
            case 9 : return "Contribution Percentage";
            case 10 : return "Frequency";
            case 11 : return "Add Permit Outlet";
            case 12 : return "Edit Permit Outlet";
            case 13 : return "Actual Outlet(Qty)";
            case 14 : return "Status";
            case 15 : return "SO ID";
            case 16 : return "Assign Day";

            default: return "Header" + (index+1);
        }
    }

    //    @Override
//    public void exportDataToExcel(HttpServletResponse response) throws IOException {
//        List<Route> routes = routeRepository.findAll(); // You should have a RouteRepository to fetch data
//
//        // Create a new workbook
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Routes Data");
//
//        // Create a header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("SL");
//        headerRow.createCell(1).setCellValue("Zone Name");
//        headerRow.createCell(2).setCellValue("Region Name");
//        headerRow.createCell(3).setCellValue("DB ID");
//        headerRow.createCell(4).setCellValue("DB Name");
//        headerRow.createCell(5).setCellValue("App");
//        headerRow.createCell(6).setCellValue("Update");
//        headerRow.createCell(7).setCellValue("Route ID");
//        headerRow.createCell(8).setCellValue("Route Name");
//        headerRow.createCell(9).setCellValue("Contribution Percentage");
//        headerRow.createCell(10).setCellValue("Frequency");
//        headerRow.createCell(11).setCellValue("Add Permit Outlet");
//        headerRow.createCell(12).setCellValue("Edit Permit Outlet");
//        headerRow.createCell(13).setCellValue("Actual Outlet(Qty)");
//        headerRow.createCell(14).setCellValue("Status");
//        headerRow.createCell(15).setCellValue("SO ID ");
//        headerRow.createCell(16).setCellValue("Assign Day");
//
//        int rowNum = 1;
//        for (Route route : routes) {
//            Row row = sheet.createRow(rowNum++);
//
//            row.createCell(0).setCellValue(rowNum - 1);
//
//            row.createCell(1).setCellValue(route.getZone().getZoneName());
//            row.createCell(2).setCellValue(route.getZone().getRegion().getRegionName());
//            row.createCell(3).setCellValue(route.getDistributor().getDistributorId());
//            row.createCell(4).setCellValue(route.getDistributor().getName());
//            row.createCell(5).setCellValue(route.getDistributor().isHasLiveApp());
//            row.createCell(6).setCellValue(route.getUpdatedAt());
//            row.createCell(7).setCellValue(route.getRouteId());
//            row.createCell(8).setCellValue(route.getRouteName());
//            row.createCell(9).setCellValue(route.getContributionPercentage());
//
//            if (route.getRouteSalesOfficerList() != null) {
//                row.createCell(10).setCellValue(route.getRouteSalesOfficerList().iterator().next().getVisitingFrequency());
//                row.createCell(11).setCellValue(route.getRouteSalesOfficerList().iterator().next().isAddPermit());
//                row.createCell(12).setCellValue(route.getRouteSalesOfficerList().iterator().next().isEditPermit());
//                row.createCell(13).setCellValue("");
//                row.createCell(14).setCellValue(route.getRouteSalesOfficerList().iterator().next().isStatus());
//
//                if (route.getRouteSalesOfficerList().iterator().next().getEmployee() != null) {
//                    row.createCell(15).setCellValue(route.getRouteSalesOfficerList().iterator().next().getEmployee().getEmployeeId());
//                }else {
//                    row.createCell(15).setCellValue("");
//                }
//                row.createCell(16).setCellValue(route.getRouteSalesOfficerList().iterator().next().getPreferredDays());
//            } else {
//                row.createCell(10).setCellValue("");
//                row.createCell(11).setCellValue("");
//                row.createCell(12).setCellValue("");
//                row.createCell(13).setCellValue("");
//                row.createCell(14).setCellValue("");
//                row.createCell(15).setCellValue("");
//                row.createCell(16).setCellValue("");
//            }
//
//
//        }
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=routes.xlsx");
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        outputStream.close();
//        workbook.close();
//    }


}
