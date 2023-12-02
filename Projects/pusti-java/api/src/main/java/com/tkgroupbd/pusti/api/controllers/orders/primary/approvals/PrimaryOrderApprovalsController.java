package com.tkgroupbd.pusti.api.controllers.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovals;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PrimaryOrderApprovalsRequest;
import com.tkgroupbd.pusti.api.services.orders.primary.PrimaryOrderApprovalsServices;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Primary Order Approval Process")
@RequestMapping("/primaryOrderApprovalsController")
public class PrimaryOrderApprovalsController {

    @Autowired
    PrimaryOrderApprovalsServices primaryOrderApprovalsServices;

    @PostMapping("/add")
    public PrimaryOrderApprovals savePrimaryOrderApprovalsWithOther(
            @RequestBody PrimaryOrderApprovalsRequest primaryOrderApprovalsRequest) {
        return primaryOrderApprovalsServices.savePrimaryOrderApprovalsWithOther(primaryOrderApprovalsRequest);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<PrimaryOrderApprovals>> getAllPrimaryOrderApprovals() {
        List<PrimaryOrderApprovals> primaryOrderApprovals = primaryOrderApprovalsServices.getAllPrimaryOrderApprovals();
        return new ResponseEntity<>(primaryOrderApprovals, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PrimaryOrderApprovals> getPrimaryOrderApprovalsById(@PathVariable("id") Long id) {
        PrimaryOrderApprovals primaryOrderApprovals = primaryOrderApprovalsServices.getPrimaryOrderApprovalsById(id);
        return new ResponseEntity<>(primaryOrderApprovals, HttpStatus.OK);
    }

}
