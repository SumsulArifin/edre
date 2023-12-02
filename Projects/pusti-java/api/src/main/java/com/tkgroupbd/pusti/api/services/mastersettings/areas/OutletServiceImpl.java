package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.OutletRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.OutletRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.RouteRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DistributorRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class OutletServiceImpl implements OutletService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private OutletRepository outletRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private RouteRepository routeRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistributorRepository distributorRepository;

    @Override
    public MessageResponse createOutlet(OutletRequest request) {
        try {
            Outlet outlet = new Outlet();
            outlet.setOutletName(request.getOutletName().trim().toUpperCase());
            outlet.setAddress(request.getAddress());
            outlet.setContactPerson(request.getContactPerson());
            outlet.setMobile(request.getMobile());
            outlet.setSalesPerMonth(request.getSalesPerMonth());
            outlet.setMarketSize(request.getMarketSize());
            outlet.setKeyOutlet(request.getKeyOutlet());
            outlet.setOutletType(request.getOutletType());
            outlet.setOutletChannel(request.getOutletChannel());
            outlet.setDisplayOutlet(request.isDisplayOutlet());
            outlet.setDisplayOutletAmount(request.isDisplayOutletAmount());
            outlet.setPaidAmount(request.getPaidAmount());
            outlet.setCreditSales(request.getCreditSales());
            outlet.setShopType(request.getShopType());
            outlet.setComments(request.getComments());
            outlet.setSalesGroup(request.getSalesGroup());
            outlet.setLongitude(request.getLongitude());
            outlet.setLatitude(request.getLatitude());
            outlet.setRoute(request.getRoute());
            outlet.setDistributor(request.getDistributor());
            outlet.setStatus(request.isStatus());

            outletRepository.save(outlet);
            return new MessageResponse(Message.SUCCESS_OUTLET_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_OUTLET_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<Outlet> updateOutlet(int id, OutletRequest request) {

        Optional<Outlet> result = Optional.ofNullable(outletRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Outlet  with id " + id + " not found")));
        if (result.isPresent()) {
            Outlet outlet = result.get();

            outlet.setOutletName(request.getOutletName().trim().toUpperCase());
            outlet.setAddress(request.getAddress().trim());
            outlet.setContactPerson(request.getContactPerson().trim());
            outlet.setMobile(request.getMobile().trim());
            outlet.setSalesPerMonth(request.getSalesPerMonth());
            outlet.setMarketSize(request.getMarketSize());
            outlet.setKeyOutlet(request.getKeyOutlet());
            outlet.setOutletType(request.getOutletType());
            outlet.setOutletChannel(request.getOutletChannel());
            outlet.setDisplayOutlet(request.isDisplayOutlet());
            outlet.setDisplayOutletAmount(request.isDisplayOutletAmount());
            outlet.setPaidAmount(request.getPaidAmount());
            outlet.setCreditSales(request.getCreditSales());
            outlet.setShopType(request.getShopType());
            outlet.setComments(request.getComments());
            outlet.setSalesGroup(request.getSalesGroup());
            outlet.setLongitude(request.getLongitude());
            outlet.setLatitude(request.getLatitude());
            outlet.setRoute(request.getRoute());
            outlet.setDistributor(request.getDistributor());
            outlet.setStatus(request.isStatus());

            outletRepository.save(outlet);
        }
        return result;
    }

    @Override
    public MessageResponse updateOutletLatLong(int outletId, OutletRequest outletRequest) {
        Optional<Outlet> existingOutletOptional = outletRepository.findById(outletId);

        if (!existingOutletOptional.isPresent()) {
            return new MessageResponse(Message.FAILED_UPDATE);
        } else {
            Outlet existingOutlet = existingOutletOptional.get();
            existingOutlet.setLongitude(outletRequest.getLongitude());
            existingOutlet.setLatitude(outletRequest.getLatitude());
            outletRepository.save(existingOutlet);
            return new MessageResponse(Message.SUCCESS_UPDATE);
        }
    }

    public List<Outlet> outletList() {
        return outletRepository.findAll();
    }

    public Optional<Outlet> outletById(int id) {
        return Optional.ofNullable(
                outletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Outlet", "id", id)));
    }

    public List<Outlet> getByOutletName(String outletName) {
        return outletRepository.findByOutletName(outletName);

    }

    public List<Outlet> getAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(String zoneName) {
        List<Outlet> outletList = outletRepository.findAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(zoneName.toUpperCase().trim());
        Collections.sort(outletList, Comparator.comparing(Outlet::getOutletName));
        return outletList;
    }

    @Override
    public Optional<Outlet> statusChangeOutlet(int id, OutletRequest request) {
        Optional<Outlet> result = Optional
                .ofNullable(outletRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Outlet  with id " + id + " not found")));
        if (result.isPresent()) {
            Outlet outlet = result.get();
            outlet.setStatus(request.isStatus());
            outletRepository.save(outlet);
        }
        return result;
    }

    public MessageResponse uploadCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<String> errorMessages = new ArrayList<>();

            reader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 19) {
                    Outlet outlet = new Outlet();
                    try {
                        outlet.setOutletName(data[0]);
                        outlet.setAddress(data[1]);
                        outlet.setContactPerson(data[2]);
                        outlet.setMobile(data[3]);
                        outlet.setSalesPerMonth(Integer.parseInt(data[4]));
                        outlet.setMarketSize(Double.parseDouble(data[5]));
                        outlet.setKeyOutlet(data[6]);
                        outlet.setOutletType(Integer.parseInt(data[7]));
                        outlet.setOutletChannel(Integer.parseInt(data[8]));
                        outlet.setDisplayOutlet(Boolean.parseBoolean(data[9]));
                        outlet.setDisplayOutletAmount(Boolean.parseBoolean(data[10]));
                        outlet.setPaidAmount(Double.parseDouble(data[11]));
                        outlet.setCreditSales(data[12]);
                        outlet.setShopType(data[13]);
                        outlet.setComments(data[14]);
                        outlet.setSalesGroup(data[15]);
                        outlet.setStatus(Boolean.parseBoolean(data[16]));

                        int routeId = Integer.parseInt(data[17]);
                        int distributorId = Integer.parseInt(data[18]);

                        Optional<Route> route = routeRepository.findById(routeId);
                        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
                        if (route.isPresent() && distributor.isPresent()) {
                            outlet.setRoute(route.get());
                            outlet.setDistributor(distributor.get());
                            outletRepository.save(outlet);
                        } else {
                            errorMessages.add("Invalid category ID or brand ID at line: " + line);
                        }
                    } catch (Exception e) {
                        errorMessages.add("Error processing line: " + line + ". Reason: " + e.getMessage());
                    }
                } else {
                    errorMessages.add("Invalid data at line: " + line);
                }
            });
            if (errorMessages.isEmpty()) {
                return new MessageResponse(Message.SUCCESS_CSV_UPLOAD);
            } else {
                StringBuilder errorMessage = new StringBuilder("Errors occurred while processing CSV file:\n");
                for (String error : errorMessages) {
                    errorMessage.append(error).append("\n");
                }
                return new MessageResponse(errorMessage.toString());
            }
        } catch (Exception e) {
            return new MessageResponse("Error while processing CSV file: " + e.getMessage());
        }
    }


    @Override
    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        List<Outlet> outletList = outletRepository.findAll(); // You should have a RouteRepository to fetch data

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Outlets Data");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Serial No");
        headerRow.createCell(1).setCellValue("Zone Name");
        headerRow.createCell(2).setCellValue("Region Name");
        headerRow.createCell(3).setCellValue("DB ID");
        headerRow.createCell(4).setCellValue("DB Name");
        headerRow.createCell(5).setCellValue("Route Id");
        headerRow.createCell(6).setCellValue("Route Name");
        headerRow.createCell(7).setCellValue("Outlet Id");
        headerRow.createCell(8).setCellValue("Outlet Name");
        headerRow.createCell(9).setCellValue("Sales Group");
        headerRow.createCell(10).setCellValue("Address");
        headerRow.createCell(11).setCellValue("Contact Person");
        headerRow.createCell(12).setCellValue("Mobile");
        headerRow.createCell(13).setCellValue("Sales / Month");
        headerRow.createCell(14).setCellValue("Market Size");
        headerRow.createCell(15).setCellValue("Outlet Channel");
        headerRow.createCell(16).setCellValue("Key Outlet");
        headerRow.createCell(17).setCellValue("Outlet Type");
        headerRow.createCell(18).setCellValue("Shop Type");
        headerRow.createCell(19).setCellValue("Display Outlet");
        headerRow.createCell(20).setCellValue("Display Outlet Amount");
        headerRow.createCell(21).setCellValue("Credit Sales");
        headerRow.createCell(22).setCellValue("Comments");
        headerRow.createCell(23).setCellValue("Status");
        headerRow.createCell(24).setCellValue("Created At");
        headerRow.createCell(25).setCellValue("Created By");
        headerRow.createCell(26).setCellValue("Updated At");
        headerRow.createCell(27).setCellValue("Updated By");
        headerRow.createCell(28).setCellValue("Deleted At");
        headerRow.createCell(29).setCellValue("Deleted By");


        int rowNum = 1;
        for (Outlet outlet : outletList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(rowNum-1);
            if (outlet.getRoute()!=null) {
                row.createCell(1).setCellValue(outlet.getRoute().getZone().getZoneName());
                row.createCell(2).setCellValue(outlet.getRoute().getZone().getRegion().getRegionName());
                row.createCell(3).setCellValue(outlet.getRoute().getDistributor().getDistributorId());
                row.createCell(4).setCellValue(outlet.getRoute().getDistributor().getName());
                row.createCell(5).setCellValue(outlet.getRoute().getRouteId());
                row.createCell(6).setCellValue(outlet.getRoute().getRouteName());
            }else {
                row.createCell(1).setCellValue("");
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("");
                row.createCell(4).setCellValue("");
                row.createCell(5).setCellValue("");
                row.createCell(6).setCellValue("");
            }




            row.createCell(7).setCellValue(outlet.getOutletId());
            row.createCell(8).setCellValue(outlet.getOutletName());
            row.createCell(9).setCellValue(outlet.getSalesGroup());
            row.createCell(10).setCellValue(outlet.getAddress());
            row.createCell(11).setCellValue(outlet.getContactPerson());
            row.createCell(12).setCellValue(outlet.getMobile());
            row.createCell(13).setCellValue(outlet.getSalesGroup());
            row.createCell(14).setCellValue(outlet.getMarketSize());
            row.createCell(15).setCellValue(outlet.getOutletChannel());
            row.createCell(16).setCellValue(outlet.getKeyOutlet());
            row.createCell(17).setCellValue(outlet.getOutletType());
            row.createCell(18).setCellValue(outlet.getShopType());
            row.createCell(19).setCellValue(outlet.isDisplayOutlet());
            row.createCell(20).setCellValue(outlet.isDisplayOutletAmount());
            row.createCell(21).setCellValue(outlet.getCreditSales());
            row.createCell(22).setCellValue(outlet.getComments());
            row.createCell(23).setCellValue(outlet.isStatus());
            row.createCell(24).setCellValue(outlet.getCreatedAt());
            row.createCell(25).setCellValue(outlet.getCreatedBy());
            row.createCell(26).setCellValue(outlet.getUpdatedAt());
            row.createCell(27).setCellValue(outlet.getUpdatedBy());
            row.createCell(28).setCellValue(outlet.getDeletedAt());
            row.createCell(29).setCellValue(outlet.getDeletedBy());

        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=outlets.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    @Override
    public Page<Outlet> findOutletsByPagination(int offset, int pageSize) {
        Page<Outlet> outlets = outletRepository.findAll(PageRequest.of(offset, pageSize));
        return outlets;
    }

}
