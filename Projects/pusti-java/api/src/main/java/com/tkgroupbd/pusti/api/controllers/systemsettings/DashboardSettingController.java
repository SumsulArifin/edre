package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DashboardSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DashboardSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.DashboardSettingServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Dashboard Settings")
@RestController
@RequestMapping("/dashboardsetting")
public class DashboardSettingController {
    @Autowired
    DashboardSettingServiceImpl service;

    // create new Dashboard Setting
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createDashboardSetting(@RequestBody DashboardSettingRequest request) {
        MessageResponse newDash = service.createDashboardSetting(request);
        return new ResponseEntity<>(newDash, HttpStatus.CREATED);
    }

    // retrieve all Dashboard Setting
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<DashboardSetting>> getAllDashboardSetting() {
        List<DashboardSetting> dashboardSettings = service.getAllDashboardSetting();
        return new ResponseEntity<>(dashboardSettings, HttpStatus.OK);
    }

    // update Dashboard Setting
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<DashboardSetting>> updateDashboardSetting(@PathVariable Integer id,
            @RequestBody DashboardSettingRequest request) {
        Optional<DashboardSetting> dashboardSetting = service.updateDashboardSetting(id, request);
        return new ResponseEntity<Optional<DashboardSetting>>(dashboardSetting, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/status/change/{id}")
    public ResponseEntity<Optional<DashboardSetting>> dashboardSettingStatusChange(@PathVariable Integer id,
            @RequestBody DashboardSettingRequest request) {
        Optional<DashboardSetting> dashboardSetting = service.dashboardSettingStatusChange(id, request);
        return new ResponseEntity<Optional<DashboardSetting>>(dashboardSetting, HttpStatus.OK);
    }

    // Delete Employee by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDashboardSetting(@PathVariable("id") Integer id) {
        service.deleteDashboardSetting(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve DashboardSetting by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<DashboardSetting> getDashboardSettingById(@PathVariable("id") Integer id) {
        DashboardSetting dashboardSetting = service.getDashboardSettingById(id);
        return new ResponseEntity<>(dashboardSetting, HttpStatus.OK);
    }

    @GetMapping("/byPagination/{offset}/{pageSize}")
    private ApiResponse<Page<DashboardSetting>> findDashboardSettingByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<DashboardSetting> dashboardSettings = service.findDashboardSettingByPagination(offset, pageSize);
        return new ApiResponse(dashboardSettings.getSize(), dashboardSettings);
    }

    @GetMapping("/getSortedByPagination/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<DashboardSetting>> findSortedDashboardSettingByPagination(@PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<DashboardSetting> dashboardSettings = service.findSortedDashboardSettingByPagination(offset, pageSize,
                field);
        return new ApiResponse(dashboardSettings.getSize(), dashboardSettings);
    }

}
