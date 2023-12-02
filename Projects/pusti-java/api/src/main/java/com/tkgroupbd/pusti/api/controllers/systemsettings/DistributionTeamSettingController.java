package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.DistributionTeamSettingServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Distribution Team Settings")
@RestController
@RequestMapping("/distributionTeamSetting")
public class DistributionTeamSettingController {

    @Autowired
    DistributionTeamSettingServiceImpl service;

    // Distribution Team Setting
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createDistributionTeamSetting(
            @RequestBody DistributionTeamSettingRequest request) {
        MessageResponse newDis = service.createDistributionTeamSetting(request);
        return new ResponseEntity<>(newDis, HttpStatus.CREATED);
    }

    // retrieve all Distribution Team Setting
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<DistributionTeamSetting>> getAllDistributionTeamSetting() {
        List<DistributionTeamSetting> teamSettings = service.getAllDistributionTeamSetting();
        return new ResponseEntity<>(teamSettings, HttpStatus.OK);
    }

    // update Distribution Team Setting
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<DistributionTeamSetting>> updateDistributionTeamSetting(@PathVariable Integer id,
            @RequestBody DistributionTeamSettingRequest request) {
        Optional<DistributionTeamSetting> teamSetting = service.updateDistributionTeamSetting(id, request);
        return new ResponseEntity<Optional<DistributionTeamSetting>>(teamSetting, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<DistributionTeamSetting>> distributionTeamSettingStatusChange(
            @PathVariable Integer id,
            @RequestBody DistributionTeamSettingRequest request) {
        Optional<DistributionTeamSetting> teamSetting = service.distributionTeamSettingStatusChange(id, request);
        return new ResponseEntity<Optional<DistributionTeamSetting>>(teamSetting, HttpStatus.OK);
    }

    // Delete Distribution Team Setting by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDistributionTeamSetting(@PathVariable("id") Integer id) {
        service.deleteDistributionTeamSetting(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve DistributionTeamSetting by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<DistributionTeamSetting> getDistributionTeamSettingById(@PathVariable("id") Integer id) {
        DistributionTeamSetting teamSetting = service.getDistributionTeamSettingById(id);
        return new ResponseEntity<>(teamSetting, HttpStatus.OK);
    }

    @GetMapping("/byPagination/{offset}/{pageSize}")
    private ApiResponse<Page<DistributionTeamSetting>> findDistributionTeamSettingByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<DistributionTeamSetting> teamSettings = service.findDistributionTeamSettingByPagination(offset, pageSize);
        return new ApiResponse(teamSettings.getSize(), teamSettings);
    }

    @GetMapping("/findSortedByPagination/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<DistributionTeamSetting>> findSortedDistributionTeamSettingByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<DistributionTeamSetting> teamSettings = service.findSortedDistributionTeamSettingByPagination(offset,
                pageSize, field);
        return new ApiResponse(teamSettings.getSize(), teamSettings);
    }

}
