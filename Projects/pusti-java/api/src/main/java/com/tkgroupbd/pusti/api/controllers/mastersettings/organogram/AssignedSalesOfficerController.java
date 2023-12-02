package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.AssignedSalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.AssignedSalesOfficerService;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.AssignedSalesOfficerServiceImpl;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Assigned Sales Officer")
@RestController
@RequestMapping("/assignedSalesOfficer")
public class AssignedSalesOfficerController {

    @Autowired
    AssignedSalesOfficerService assignedSalesOfficerService;

    @PostMapping("/addNewSalesOfficer")
    public ResponseEntity<MessageResponse> assignedSalesOfficerSave(
            @RequestBody @Valid AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        MessageResponse newAssignedSalesOfficer = assignedSalesOfficerService
                .saveAssignedSalesOfficer(assigned_sales_officerRequest);
        return new ResponseEntity<>(newAssignedSalesOfficer, HttpStatus.CREATED);
    }

    @GetMapping("/getAllSalesOfficers")
    public ResponseEntity<List<AssignedSalesOfficer>> getAssignedSalesOfficer() {
        List<AssignedSalesOfficer> assigned_sales_officers = assignedSalesOfficerService
                .getAllAssignedSalesOfficer();
        return new ResponseEntity<>(assigned_sales_officers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSalesOfficerById/{assignedId}")
    public ResponseEntity<?> deleteDistributorById(@PathVariable("assignedId") Integer assignedId) {
        assignedSalesOfficerService.deleteAssignedSalesOfficerById(assignedId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSalesOfficerById/{assignedId}")
    public ResponseEntity<AssignedSalesOfficer> getDistributorById(@PathVariable("assignedId") Integer assignedId) {
        AssignedSalesOfficer assigned_sales_officer = assignedSalesOfficerService
                .findAssignedSalesOfficerById(assignedId);
        return new ResponseEntity<>(assigned_sales_officer, HttpStatus.OK);
    }

    @PutMapping("/updateSalesOfficer/{assignedId}")
    public ResponseEntity<Optional<AssignedSalesOfficer>> updateDistributor(@PathVariable Integer assignedId,
            @RequestBody @Valid AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        Optional<AssignedSalesOfficer> assignedSalesOfficer = assignedSalesOfficerService
                .updateAssignedSalesOfficer(assignedId, assigned_sales_officerRequest);
        return new ResponseEntity<Optional<AssignedSalesOfficer>>(assignedSalesOfficer, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{assignedId}")
    public ResponseEntity<Optional<AssignedSalesOfficer>> updateDistributorStatus(@PathVariable Integer assignedId,
            @RequestBody AssignedSalesOfficerRequest assigned_sales_officerRequest) {
        Optional<AssignedSalesOfficer> assigned_sales_officer = assignedSalesOfficerService
                .updateAssignedSalesOfficerStatus(assignedId, assigned_sales_officerRequest);
        return new ResponseEntity<Optional<AssignedSalesOfficer>>(assigned_sales_officer, HttpStatus.OK);
    }

    @GetMapping("/getSortedSalesOfficersByKey/{field}")
    private ApiResponse<List<AssignedSalesOfficer>> getSortedDistributorByKey(@PathVariable String field) {
        List<AssignedSalesOfficer> assignedSalesOfficer = assignedSalesOfficerService
                .findSortedAssignedSalesOfficerByKey(field);
        return new ApiResponse(assignedSalesOfficer.size(), assignedSalesOfficer);
    }

    @GetMapping("/getSoByRouteId/{routeId}")
    public List<AssignedSalesOfficer> soFindByRouteId(@PathVariable String routeId) {
        int routeIds = Integer.parseInt(routeId);
        return assignedSalesOfficerService.getAssignedSalesOfficerListByRouteID(routeIds);
    }

    @PostMapping("/save-multiple")
    public ResponseEntity<MessageResponse> saveMultipleAssignedSalesOfficers(
            @RequestBody List<AssignedSalesOfficerRequest> assignedSalesOfficerRequests) {
        MessageResponse response = assignedSalesOfficerService.saveAssignedSalesOfficers(assignedSalesOfficerRequests);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * upload csv file
     * 
     * @param file
     */

    @PostMapping("/csv-upload")
    public ResponseEntity<MessageResponse> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        MessageResponse response = assignedSalesOfficerService.uploadAndProcessCSV(file);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
