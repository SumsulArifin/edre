package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.AssignedSalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Component
public interface AssignedSalesOfficerService {

        MessageResponse saveAssignedSalesOfficer(AssignedSalesOfficerRequest assigned_sales_officerRequest);

        public Optional<AssignedSalesOfficer> updateAssignedSalesOfficer(int assignedId,
                        AssignedSalesOfficerRequest assigned_sales_officerRequest);

        public void deleteAssignedSalesOfficerById(int assignedId);

        public Optional<AssignedSalesOfficer> updateAssignedSalesOfficerStatus(int assignedId,
                        AssignedSalesOfficerRequest assigned_sales_officerRequest);

        List<AssignedSalesOfficer> findSortedAssignedSalesOfficerByKey(String field);

        public List<AssignedSalesOfficer> getAllAssignedSalesOfficer();

        public AssignedSalesOfficer findAssignedSalesOfficerById(int assignedId);

        public List<AssignedSalesOfficer> getAssignedSalesOfficerListByRouteID(int route_id);

        public MessageResponse uploadAndProcessCSV(MultipartFile file);
        public MessageResponse saveAssignedSalesOfficers(List<AssignedSalesOfficerRequest> assignedSalesOfficerRequests);

}
