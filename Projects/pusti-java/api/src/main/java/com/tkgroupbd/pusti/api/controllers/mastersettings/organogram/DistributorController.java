package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.DistributorRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.DistributorService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Distributor ")
@RestController
@RequestMapping("/distributor")
public class DistributorController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistributorService distributorService;

    @PostMapping("/addNewDistributor")
    public ResponseEntity<MessageResponse> distributorSave(@RequestBody @Valid DistributorRequest distributorsRequest) {

        MessageResponse newDistributor = distributorService.saveDistributor(distributorsRequest);
        return new ResponseEntity<>(newDistributor, HttpStatus.CREATED);
    }

    @GetMapping("/getAllDistributors")
    public ResponseEntity<List<Distributor>> getAllDistributors() {
        List<Distributor> distributors = distributorService.getAllDistributors();
        return new ResponseEntity<>(distributors, HttpStatus.OK);
    }

    @GetMapping("/getDistributorById/{distributorId}")
    public ResponseEntity<Distributor> getDistributorById(@PathVariable("distributorId") Integer distributorId) {
        Distributor distributor = distributorService.findDistributorById(distributorId);
        return new ResponseEntity<>(distributor, HttpStatus.OK);
    }

    @PutMapping("/updateDistributor/{distributorId}")
    public ResponseEntity<Optional<Distributor>> updateDistributor(@PathVariable Integer distributorId,
            @RequestBody @Valid DistributorRequest distributorsRequest) {
        Optional<Distributor> distributor = distributorService.updateDistributor(distributorId, distributorsRequest);
        return new ResponseEntity<Optional<Distributor>>(distributor, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{distributorId}")
    public ResponseEntity<Optional<Distributor>> updateDistributorStatus(
            @PathVariable Integer distributorId,
            @RequestBody DistributorRequest distributorsRequest) {
        Optional<Distributor> distributor = distributorService.updateDistributorStatus(distributorId,
                distributorsRequest);
        return new ResponseEntity<Optional<Distributor>>(distributor, HttpStatus.OK);
    }

    @GetMapping("/getDistributorsByDistributorName")
    private ApiResponse<List<Distributor>> getSortedDistributorByKey(@RequestParam("field") String field) {
        List<Distributor> distributors = distributorService.getDistributorByDistributorName(field);
        return new ApiResponse(distributors.size(), distributors);
    }

    @GetMapping("/getDistributorByZoneName")
    private ApiResponse<List<Distributor>> getDistributorByZoneName(@RequestParam("field") String field) {
        List<Distributor> distributors = distributorService.getDistributorByZoneName(field);
        return new ApiResponse(distributors.size(), distributors);
    }

    @GetMapping("/getDistributorByUpazilaId/{upazilaId}")
    private ResponseEntity<List<Distributor>> getDistributorsByUpazilaId(@PathVariable("upazilaId") Integer upazilaId) {
        List<Distributor> distributors = distributorService.getDistributorsByUpazilaId(upazilaId);
        return new ResponseEntity<>(distributors, HttpStatus.OK);
    }

    @GetMapping("/getPaginatedDistributors")
    private ResponseEntity<Page<Distributor>> getPaginatedDistributors(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Distributor> paginatedDistributors = distributorService.findDistributorsByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedDistributors,HttpStatus.OK);
    }
}
