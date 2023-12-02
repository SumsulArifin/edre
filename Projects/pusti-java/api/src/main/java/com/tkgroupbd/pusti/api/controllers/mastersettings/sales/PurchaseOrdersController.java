package com.tkgroupbd.pusti.api.controllers.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.PurchaseOrdersRequest;
import com.tkgroupbd.pusti.api.services.mastersettings.sales.PurchaseOrdersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Purchase Orders")
@RestController
@RequestMapping("/purchaseOrders")
public class PurchaseOrdersController {
    @Autowired
    PurchaseOrdersService purchaseOrdersService;

    @GetMapping("/getAllPurchaseOrders")
    public ResponseEntity<List<PurchaseOrders>> getAllOrder() {
        List<PurchaseOrders> orders = purchaseOrdersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getPurchaseOrderById/{orderId}")
    public ResponseEntity<PurchaseOrders> getOrdersById(@PathVariable("orderId") Integer orderId) {
        PurchaseOrders orders = purchaseOrdersService.findOrdersById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/addNewPurchaseOrder")
    public ResponseEntity<PurchaseOrders> addOrders(
            @RequestBody @Valid PurchaseOrdersRequest orderRequest) {
        PurchaseOrders newOrders = purchaseOrdersService.saveOrder(orderRequest);
        return new ResponseEntity<>(newOrders, HttpStatus.CREATED);
    }

    @PutMapping("/updatePurchaseOrder/{orderId}")
    public ResponseEntity<Optional<PurchaseOrders>> updateOrderById(@PathVariable Integer orderId,
            @RequestBody @Valid PurchaseOrdersRequest orderRequest) {
        Optional<PurchaseOrders> orders = purchaseOrdersService.updateOrders(orderId, orderRequest);
        return new ResponseEntity<Optional<PurchaseOrders>>(orders, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<Optional<PurchaseOrders>> updateSalesOrganizationStatus(@PathVariable Integer orderId,
            @RequestBody PurchaseOrdersRequest orderRequest) {
        Optional<PurchaseOrders> orders = purchaseOrdersService.updateOrdersStatus(orderId, orderRequest);
        return new ResponseEntity<Optional<PurchaseOrders>>(orders, HttpStatus.OK);
    }
    @GetMapping("/getPaginatedBrands")
    private Page<PurchaseOrders> getPaginatedBrands(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrdersByPagination(offset, pageSize);
        return purchaseOrders;
    }


}
