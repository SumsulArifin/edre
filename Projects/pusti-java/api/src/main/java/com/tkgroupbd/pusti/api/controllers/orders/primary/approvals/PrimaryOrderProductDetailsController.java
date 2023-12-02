package com.tkgroupbd.pusti.api.controllers.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderProductDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderProductDetailsRequest;
import com.tkgroupbd.pusti.api.services.orders.primary.approvals.PrimaryOrderProductDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PrimaryOrder Product Details")
@RestController
@RequestMapping("/primaryOrderProductDetails")
public class PrimaryOrderProductDetailsController {

    @Autowired
    PrimaryOrderProductDetailsService primaryOrderProductDetailsService;

    @PostMapping("/addPrimaryOrderProduct")
    public ResponseEntity<Integer> addPrimaryOrders(
            @RequestBody PrimaryOrderProductDetailsRequest primaryOrderProductDetails) {
        PrimaryOrderProductDetails savedPrimaryOrder = primaryOrderProductDetailsService
                .savePrimaryOrderProductDetails(primaryOrderProductDetails);
        int primaryOrderId = primaryOrderProductDetailsService.getPrimaryOrderProductDetailsId(savedPrimaryOrder);
        return ResponseEntity.ok(primaryOrderId);
    }
}
