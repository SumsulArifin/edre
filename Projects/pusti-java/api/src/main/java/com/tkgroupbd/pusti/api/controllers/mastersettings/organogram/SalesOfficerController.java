package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.sales.SalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.SalesOfficerService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sales Officer")
@RestController
@RequestMapping("/salesOfficer")
public class SalesOfficerController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private SalesOfficerService salesOfficerService;

    @PostMapping("/addSalesOfficer")
    public ResponseEntity<MessageResponse> addSalesOfficer(
            @RequestBody @Valid SalesOfficerRequest salesOfficerRequest) {
        MessageResponse salesOfficer = salesOfficerService.createSalesOfficer(salesOfficerRequest);
        return new ResponseEntity<>(salesOfficer, HttpStatus.CREATED);
    }

    @GetMapping("/getAllSalesOfficers")
    public ResponseEntity<List<SalesOfficer>> getAllSalesOfficers() {
        List<SalesOfficer> salesOfficers = salesOfficerService.getAllSalesOfficers();
        return new ResponseEntity<>(salesOfficers, HttpStatus.OK);
    }

    @GetMapping("/getSalesOfficerById/{salesOfficerId}")
    public ResponseEntity<SalesOfficer> getDistributorById(@PathVariable("salesOfficerId") Integer salesOfficerId) {
        SalesOfficer salesOfficer = salesOfficerService.findSalesOfficerById(salesOfficerId);
        return new ResponseEntity<>(salesOfficer, HttpStatus.OK);
    }

    @PutMapping("/updateSalesOfficer/{salesOfficerId}")
    public ResponseEntity<Optional<SalesOfficer>> updateSalesOfficer(@PathVariable Integer salesOfficerId,
            @RequestBody @Valid SalesOfficerRequest salesOfficerRequest) {
        Optional<SalesOfficer> assignedSalesOfficer = salesOfficerService.updateSalesOfficer(salesOfficerId,
                salesOfficerRequest);
        return new ResponseEntity<Optional<SalesOfficer>>(assignedSalesOfficer, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{salesOfficerId}")
    public ResponseEntity<Optional<SalesOfficer>> updateSalesOfficerStatus(@PathVariable Integer salesOfficerId,
            @RequestBody SalesOfficerRequest salesOfficerRequest) {

        Optional<SalesOfficer> salesOfficer = salesOfficerService.updateSalesOfficerStatus(salesOfficerId,
                salesOfficerRequest);
        return new ResponseEntity<Optional<SalesOfficer>>(salesOfficer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSalesOfficerById/{salesOfficerId}")
    public ResponseEntity<?> deleteSalesOfficerById(@PathVariable("salesOfficerId") Integer salesOfficerId) {
        salesOfficerService.deleteSalesOfficerById(salesOfficerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getPaginatedSalesOfficers")
    private ResponseEntity<Page<SalesOfficer>> getPaginatedSalesOfficers(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<SalesOfficer> paginatedSalesOfficers = salesOfficerService.findSalesOfficersByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedSalesOfficers,HttpStatus.OK);
    }
}
