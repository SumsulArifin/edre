package com.tkgroupbd.pusti.api.controllers.factory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.IssuePanelActivities;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.IssuePanelActivitiesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.inventory.IssuePanelActivitiesServices;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Issue Panel Activity")
@RequestMapping("issuePanelActivities")
public class IssuePanelActivitiesController {

    @Autowired
    IssuePanelActivitiesServices issuePanelActivitiesServices;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addIssuePanelActivities(@RequestBody IssuePanelActivitiesRequest request) {
        MessageResponse response = issuePanelActivitiesServices.addIssuePanelActivities(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{issuePanelActivitiesId}")
    public ResponseEntity<Optional<IssuePanelActivities>> updateIssuePanelActivities(
            @PathVariable int issuePanelActivitiesId, @RequestBody IssuePanelActivitiesRequest request) {
        Optional<IssuePanelActivities> issuePanelActivities = issuePanelActivitiesServices
                .updateIssuePanelActivities(issuePanelActivitiesId, request);
        return new ResponseEntity<Optional<IssuePanelActivities>>(issuePanelActivities, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IssuePanelActivities>> getAllIssuePanelActivities() {
        List<IssuePanelActivities> issuePanelActivities = issuePanelActivitiesServices.getAllIssuePanelActivities();
        return new ResponseEntity<>(issuePanelActivities, HttpStatus.OK);
    }

    @GetMapping("/getById/{issuePanelActivitiesId}")
    public ResponseEntity<IssuePanelActivities> getIssuePanelActivitiesById(
            @PathVariable("issuePanelActivitiesId") Integer issuePanelActivitiesId) {
        IssuePanelActivities issuePanelActivities = issuePanelActivitiesServices
                .getIssuePanelActivitiesById(issuePanelActivitiesId);
        return new ResponseEntity<>(issuePanelActivities, HttpStatus.OK);
    }
}
