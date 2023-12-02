package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamMember;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamMemberRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.DistributionTeamMemberServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Distribution Team")
@RestController
@RequestMapping("/distributionTeamMember")
public class DistributionTeamMemberController {

    @Autowired
    DistributionTeamMemberServiceImpl service;

    // Distribution Team member
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createDistributionTeamMember(
            @RequestBody DistributionTeamMemberRequest request) {
        MessageResponse newDis = service.createDistributionTeamMember(request);
        return new ResponseEntity<>(newDis, HttpStatus.CREATED);
    }

    // retrieve all Distribution Team member
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<DistributionTeamMember>> getAllDistributionTeamMember() {
        List<DistributionTeamMember> teamMembers = service.getAllDistributionTeamMember();
        return new ResponseEntity<>(teamMembers, HttpStatus.OK);
    }

    // update Distribution Team Setting
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<DistributionTeamMember>> updateDistributionTeamMember(@PathVariable Integer id,
            @RequestBody DistributionTeamMemberRequest request) {
        Optional<DistributionTeamMember> teamMember = service.updateDistributionTeamMember(id, request);
        return new ResponseEntity<Optional<DistributionTeamMember>>(teamMember, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<DistributionTeamMember>> distributionTeamMemberStatusChange(@PathVariable Integer id,
            @RequestBody DistributionTeamMemberRequest request) {
        Optional<DistributionTeamMember> teamMember = service.distributionTeamMemberStatusChange(id, request);
        return new ResponseEntity<Optional<DistributionTeamMember>>(teamMember, HttpStatus.OK);
    }

    // Delete Distribution Team Member by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDistributionTeamMember(@PathVariable("id") Integer id) {
        service.deleteDistributionTeamMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve DistributionTeamMember by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<DistributionTeamMember> getDistributionTeamMemberById(@PathVariable("id") Integer id) {
        DistributionTeamMember teamMember = service.getDistributionTeamMemberById(id);
        return new ResponseEntity<>(teamMember, HttpStatus.OK);
    }

    @GetMapping("/findByPagination/{offset}/{pageSize}")
    private ApiResponse<Page<DistributionTeamMember>> findDistributionTeamMemberByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<DistributionTeamMember> teamMembers = service.findDistributionTeamMemberByPagination(offset, pageSize);
        return new ApiResponse(teamMembers.getSize(), teamMembers);
    }

    @GetMapping("/findSortedPagination/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<DistributionTeamMember>> findSortedDistributionTeamMemberByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<DistributionTeamMember> teamMembers = service.findSortedDistributionTeamMemberByPagination(offset,
                pageSize, field);
        return new ApiResponse(teamMembers.getSize(), teamMembers);
    }

}
