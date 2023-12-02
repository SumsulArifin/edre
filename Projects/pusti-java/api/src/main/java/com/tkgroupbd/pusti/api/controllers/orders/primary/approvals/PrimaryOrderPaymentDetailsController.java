package com.tkgroupbd.pusti.api.controllers.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderPaymentDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderPaymentDetailsRequest;
import com.tkgroupbd.pusti.api.services.orders.primary.approvals.PrimaryOrderPaymentDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PrimaryOrder Payment Details")
@RestController
@RequestMapping("/primaryOrderPaymentDetails")
public class PrimaryOrderPaymentDetailsController {
    @Autowired
    PrimaryOrderPaymentDetailsService primaryOrderPaymentDetailsService;

    @PostMapping("/addPrimaryOrderPayment")
    public ResponseEntity<Integer> addPrimaryOrders(
            @RequestBody PrimaryOrderPaymentDetailsRequest primaryOrderProductDetails) {
        PrimaryOrderPaymentDetails savedPrimaryOrder = primaryOrderPaymentDetailsService
                .savePrimaryOrderPaymentDetails(primaryOrderProductDetails);
        int primaryOrderId = primaryOrderPaymentDetailsService.getPrimaryOrderPaymentDetailsId(savedPrimaryOrder);
        return ResponseEntity.ok(primaryOrderId);
    }
}
