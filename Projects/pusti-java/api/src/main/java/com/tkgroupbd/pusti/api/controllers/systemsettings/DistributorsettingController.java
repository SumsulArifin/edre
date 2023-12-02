package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.Distributorsetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorsettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.DistributorsettingServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Distributor Settings")
@RestController
@RequestMapping("/distributorsetting")
public class DistributorsettingController {
    @Autowired
    private DistributorsettingServiceImpl service;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> distributorsettingSave(@RequestBody DistributorsettingRequest request) {
        MessageResponse newDistributorsetting = service.saveDistributorsetting(request);
        return new ResponseEntity<>(newDistributorsetting, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Distributorsetting>> getDistributorsetting() {
        List<Distributorsetting> distributorsetting = service.getAllDistributorsetting();
        return new ResponseEntity<>(distributorsetting, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{distributorsettingId}")
    public ResponseEntity<?> deleteDistributorsetting(
            @PathVariable("distributorsettingId") Integer distributorsettingId) {
        service.deleteDistributorsettingById(distributorsettingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getById/{distributorsettingId}")
    public ResponseEntity<Distributorsetting> getDistributorsettingById(
            @PathVariable("distributorsettingId") Integer distributorsettingId) {
        Distributorsetting distributorsetting = service.findDistributorsettingById(distributorsettingId);
        return new ResponseEntity<>(distributorsetting, HttpStatus.OK);
    }

    @PutMapping("/update/{distributorsettingId}")
    public ResponseEntity<Optional<Distributorsetting>> updatedistributorsetting(
            @PathVariable("distributorsettingId") Integer distributorsettingId,
            @RequestBody DistributorsettingRequest request) {
        Optional<Distributorsetting> distributorsetting = service.updateDistributorsetting(distributorsettingId,
                request);
        return new ResponseEntity<Optional<Distributorsetting>>(distributorsetting, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{distributorsettingId}")
    public ResponseEntity<Optional<Distributorsetting>> updateDistributorsettingStatus(
            @PathVariable("distributorsettingId") Integer distributorsettingId,
            @RequestBody DistributorsettingRequest request) {
        Optional<Distributorsetting> distributorsetting = service.updateDistributorsettingStatus(distributorsettingId,
                request);
        return new ResponseEntity<Optional<Distributorsetting>>(distributorsetting, HttpStatus.OK);
    }
}
