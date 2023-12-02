package com.tkgroupbd.pusti.api.controllers.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.orders.primary.approvals.PrimaryOrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Primary Orders")
@RestController
@RequestMapping("/primaryOrder")
public class PrimaryOrderController {
    @Autowired
    PrimaryOrderService primaryOrderService;

    @PostMapping("/addPrimaryOrder")
    public void addPrimaryOrders(@RequestBody PrimaryOrder primaryOrderRequest) {
        primaryOrderService.savePrimaryOrder(primaryOrderRequest);

    }

    @PutMapping("/updatePrimaryOrder/{primaryOrderId}")
    public ResponseEntity<MessageResponse> updatePrimaryOrder(@PathVariable Integer primaryOrderId,
            @RequestBody PrimaryOrderRequest primaryOrdersRequest) {
        MessageResponse modifiedPrimaryOrder = primaryOrderService.updatePrimaryOrder(primaryOrderId,
                primaryOrdersRequest);
        return new ResponseEntity<>(modifiedPrimaryOrder, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{primaryOrderId}")
    public ResponseEntity<Optional<PrimaryOrder>> changePrimaryOrderStatus(@PathVariable Integer primaryOrderId,
            @RequestBody PrimaryOrderRequest primaryOrdersRequest) {
        Optional<PrimaryOrder> primaryOrders = primaryOrderService.updatePrimaryOrderStatus(primaryOrderId,
                primaryOrdersRequest);
        return new ResponseEntity<Optional<PrimaryOrder>>(primaryOrders, HttpStatus.OK);
    }

}
