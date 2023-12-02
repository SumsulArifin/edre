package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.AlternateChannelSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.AlternateChannelSettingsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.AlternateChannelSettingsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Alternate Channel Settings")
@RestController
@RequestMapping("/alternateChannelSettings")
public class AlternateChannelSettingsController {
    @Autowired
    private AlternateChannelSettingsServiceImpl service;

    // create new Alternate Channel Setting
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createAltChannelSettings(
            @RequestBody AlternateChannelSettingsRequest request) {
        MessageResponse newAlt = service.createAltChannelSettings(request);
        return new ResponseEntity<>(newAlt, HttpStatus.CREATED);
    }

    // retrieve all Alternate Channel Setting
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<AlternateChannelSettings>> getAllAltChannelSettings() {
        List<AlternateChannelSettings> alternateChannelSettings = service.getAllAltChannelSettings();
        return new ResponseEntity<>(alternateChannelSettings, HttpStatus.OK);
    }

    // Update a Alternate Channel Setting
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<AlternateChannelSettings>> updateAltChannelSettings(@PathVariable Integer id,
            @RequestBody AlternateChannelSettingsRequest request) {
        Optional<AlternateChannelSettings> alternateChannelSettings = service.updateAltChannelSettings(id, request);
        return new ResponseEntity<Optional<AlternateChannelSettings>>(alternateChannelSettings, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<AlternateChannelSettings>> altChannelSettingsStatusChanges(@PathVariable Integer id,
            @RequestBody AlternateChannelSettingsRequest request) {
        Optional<AlternateChannelSettings> alternateChannelSettings = service.altChannelSettingsStatusChange(id,
                request);
        return new ResponseEntity<Optional<AlternateChannelSettings>>(alternateChannelSettings, HttpStatus.OK);
    }

    // Delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAltChannelSettingsById(@PathVariable("id") Integer id) {
        service.deleteAltChannelSettings(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve AltChannelSettings by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<AlternateChannelSettings> getAltChannelSettingsById(@PathVariable("id") Integer id) {
        AlternateChannelSettings alternateChannelSettings = service.getAltChannelSettingsById(id);
        return new ResponseEntity<>(alternateChannelSettings, HttpStatus.OK);
    }

    @GetMapping("/getPaginated/{offset}/{pageSize}")
    private ApiResponse<Page<AlternateChannelSettings>> findAltChannelSettingsByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<AlternateChannelSettings> alternateChannelSettings = service.findAltChannelSettingsByPagination(offset,
                pageSize);
        return new ApiResponse(alternateChannelSettings.getSize(), alternateChannelSettings);
    }

    @GetMapping("/getSortedPaginated/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<AlternateChannelSettings>> findSortedAltChannelSettingsByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field) {
        Page<AlternateChannelSettings> alternateChannelSettings = service
                .findSortedAltChannelSettingsByPagination(offset, pageSize, field);
        return new ApiResponse(alternateChannelSettings.getSize(), alternateChannelSettings);
    }

}
