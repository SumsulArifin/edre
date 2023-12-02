package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.AssignedSalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.AssignedSalesOfficerRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DistributorRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.RouteRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.SalesOfficerRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignedSalesOfficerServiceImpl implements AssignedSalesOfficerService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    AssignedSalesOfficerRepository assignedSalesOfficerRepository;
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    RouteRepository routeRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    DistributorRepository distributorRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    SalesOfficerRepository salesOfficerRepository;

    @Override
    public MessageResponse saveAssignedSalesOfficer(AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        AssignedSalesOfficer assigned_sales_officer = new AssignedSalesOfficer();

        assigned_sales_officer.setSoName(assigned_sales_officerRequest.getSoName());
        assigned_sales_officer.setVisitedOutlet(assigned_sales_officerRequest.getVisitedOutlet());
        assigned_sales_officer.setScheduleOutlet(assigned_sales_officerRequest.getScheduleOutlet());
        assigned_sales_officer.setFrequency(assigned_sales_officerRequest.getFrequency());
        assigned_sales_officer.setStatus(assigned_sales_officerRequest.isStatus());
        assigned_sales_officer.setCreatedAt(assigned_sales_officerRequest.getCreatedAt());
        assigned_sales_officer.setUpdatedAt(assigned_sales_officerRequest.getUpdatedAt());
        assigned_sales_officer.setDeletedAt(assigned_sales_officerRequest.getDeletedAt());
        assigned_sales_officer.setBrowser(assigned_sales_officerRequest.getBrowser());
        assigned_sales_officer.setIp(assigned_sales_officerRequest.getIp());
        assigned_sales_officer.setRoute(assigned_sales_officerRequest.getRoute());
        assigned_sales_officer.setDistributor(assigned_sales_officerRequest.getDistributor());

        assignedSalesOfficerRepository.save(assigned_sales_officer);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<AssignedSalesOfficer> updateAssignedSalesOfficer(int assignedId,
            AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        Optional<AssignedSalesOfficer> result = assignedSalesOfficerRepository.findById(assignedId);
        if (result.isPresent()) {
            AssignedSalesOfficer objAssignedSO = result.get();

            objAssignedSO.setVisitedOutlet(assigned_sales_officerRequest.getVisitedOutlet());
            objAssignedSO.setScheduleOutlet(assigned_sales_officerRequest.getScheduleOutlet());
            objAssignedSO.setFrequency(assigned_sales_officerRequest.getFrequency());
            objAssignedSO.setSoName(assigned_sales_officerRequest.getSoName());
            objAssignedSO.setStatus(assigned_sales_officerRequest.isStatus());
            objAssignedSO.setCreatedAt(assigned_sales_officerRequest.getCreatedAt());
            objAssignedSO.setUpdatedAt(assigned_sales_officerRequest.getUpdatedAt());
            objAssignedSO.setDeletedAt(assigned_sales_officerRequest.getDeletedAt());
            objAssignedSO.setBrowser(assigned_sales_officerRequest.getBrowser());
            objAssignedSO.setIp(assigned_sales_officerRequest.getIp());
            objAssignedSO.setRoute(assigned_sales_officerRequest.getRoute());
            objAssignedSO.setDistributor(assigned_sales_officerRequest.getDistributor());

            assignedSalesOfficerRepository.save(objAssignedSO);

        } else
            throw new ResourceNotFoundException("Assigned_Sales_Officer", "assignedId", assignedId);

        return result;
    }

    @Override
    public void deleteAssignedSalesOfficerById(int assignedId) {
        assignedSalesOfficerRepository.deleteById(assignedId);
    }

    @Override
    public Optional<AssignedSalesOfficer> updateAssignedSalesOfficerStatus(int assignedId,
            AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        Optional<AssignedSalesOfficer> assignedSalesOfficer = assignedSalesOfficerRepository.findById(assignedId);
        if (assignedSalesOfficer.isEmpty()) {
            throw new ResourceNotFoundException("Assigned_Sales_Officer", "assignedId", assignedId);
        } else
            assignedSalesOfficer.get().setStatus(assigned_sales_officerRequest.isStatus());
        ;
        assignedSalesOfficerRepository.save(assignedSalesOfficer.get());
        return assignedSalesOfficer;
    }

    @Override
    public List<AssignedSalesOfficer> findSortedAssignedSalesOfficerByKey(String field) {
        return assignedSalesOfficerRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public List<AssignedSalesOfficer> getAllAssignedSalesOfficer() {
        return assignedSalesOfficerRepository.findAll();
    }

    @Override
    public AssignedSalesOfficer findAssignedSalesOfficerById(int assignedId) {
        return assignedSalesOfficerRepository.findById(assignedId).get();
    }

    @Override
    public List<AssignedSalesOfficer> getAssignedSalesOfficerListByRouteID(int route_id) {
        return null;// assignedSalesOfficerRepository.findAssignedSalesOfficerByRouteId(route_id);
    }

    public MessageResponse saveAssignedSalesOfficers(List<AssignedSalesOfficerRequest> assignedSalesOfficerRequests) {
        List<AssignedSalesOfficer> assignedSalesOfficers = new ArrayList<>();

        for (AssignedSalesOfficerRequest assignedSalesOfficerRequest : assignedSalesOfficerRequests) {
            AssignedSalesOfficer assignedSalesOfficer = new AssignedSalesOfficer();

            assignedSalesOfficer.setRoute(assignedSalesOfficerRequest.getRoute());
            assignedSalesOfficer.setFrequency(assignedSalesOfficerRequest.getFrequency());
            assignedSalesOfficer.setDistributor(assignedSalesOfficerRequest.getDistributor());
            assignedSalesOfficer.setSalesOfficer(assignedSalesOfficerRequest.getSalesOfficer());
            assignedSalesOfficer.setVisitedOutlet(assignedSalesOfficerRequest.getVisitedOutlet());
            assignedSalesOfficer.setSoName(assignedSalesOfficerRequest.getSoName());
            assignedSalesOfficer.setPrefireDay(assignedSalesOfficerRequest.getPrefireDay());
            assignedSalesOfficer.setScheduleOutlet(assignedSalesOfficerRequest.getScheduleOutlet());
            assignedSalesOfficer.setStatus(assignedSalesOfficerRequest.isStatus());
            assignedSalesOfficer.setCreatedAt(assignedSalesOfficerRequest.getCreatedAt());
            assignedSalesOfficer.setUpdatedAt(assignedSalesOfficerRequest.getUpdatedAt());
            assignedSalesOfficer.setDeletedAt(assignedSalesOfficerRequest.getDeletedAt());
            assignedSalesOfficer.setBrowser(assignedSalesOfficerRequest.getBrowser());
            assignedSalesOfficer.setIp(assignedSalesOfficerRequest.getIp());
            assignedSalesOfficers.add(assignedSalesOfficer);
        }

        assignedSalesOfficerRepository.saveAll(assignedSalesOfficers);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    /**
     * csv file upload method
     *
     * @param file
     * @return
     */

    @Override
    public MessageResponse uploadAndProcessCSV(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<AssignedSalesOfficer> assignedSalesOfficers = new ArrayList<>();

            // Skip the header line if it exists
            reader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length == 13) { // Ensure all columns are present
                    AssignedSalesOfficer assignedSalesOfficer = new AssignedSalesOfficer();

                    assignedSalesOfficer.setSoName(data[0]);
                    assignedSalesOfficer.setPrefireDay(data[1]);
                    assignedSalesOfficer.setScheduleOutlet(Integer.parseInt(data[2]));
                    assignedSalesOfficer.setVisitedOutlet(Integer.parseInt(data[3]));
                    assignedSalesOfficer.setStatus(Boolean.parseBoolean(data[4]));
                    assignedSalesOfficer.setCreatedAt(LocalDate.parse(data[5]));
                    assignedSalesOfficer.setUpdatedAt(LocalDate.parse(data[6]));
                    assignedSalesOfficer.setDeletedAt(LocalDate.parse(data[7]));
                    assignedSalesOfficer.setBrowser(data[8]);
                    assignedSalesOfficer.setIp(data[9]);

                    int routeId = Integer.parseInt(data[10]);
                    int distributorId = Integer.parseInt(data[11]);
                    int salesOfficerId = Integer.parseInt(data[12]);

                    Optional<Route> route = routeRepository.findById(routeId);
                    Optional<Distributor> distributor = distributorRepository.findById(distributorId);
                    Optional<SalesOfficer> salesOfficer = salesOfficerRepository.findById(salesOfficerId);

                    if (route.isPresent() && distributor.isPresent() && salesOfficer.isPresent()) {
                        assignedSalesOfficer.setRoute(route.get());
                        assignedSalesOfficer.setDistributor(distributor.get());
                        assignedSalesOfficer.setSalesOfficer(salesOfficer.get());
                        assignedSalesOfficers.add(assignedSalesOfficer);
                    }
                }
            });
            assignedSalesOfficerRepository.saveAll(assignedSalesOfficers);
            return new MessageResponse(Message.SUCCESS_CSV_UPLOAD);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload CSV file: " + e.getMessage());
        }

    }
}
