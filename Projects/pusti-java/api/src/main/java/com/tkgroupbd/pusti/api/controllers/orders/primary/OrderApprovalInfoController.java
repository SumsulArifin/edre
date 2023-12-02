package com.tkgroupbd.pusti.api.controllers.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OrderApprovalInfo;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.OrderApprovalInfoRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.orders.primary.OrderApprovalInfoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Order Approval Info")
@RestController
@RequestMapping("/orderApprovalInfo")
public class OrderApprovalInfoController {

    @Autowired
    OrderApprovalInfoServiceImpl service;

    // create Order Approval Info
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createOrderApprovalInfo(
            @RequestBody OrderApprovalInfoRequest request) {
        MessageResponse orderApprovalInfo = service.createOrderApprovalInfo(request);
        return new ResponseEntity<>(orderApprovalInfo, HttpStatus.CREATED);
    }

    // retrieve all
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<OrderApprovalInfo>> getAllOrderApprovalInfo() {
        List<OrderApprovalInfo> approvalInfos = service.getAllOrderApprovalInfo();
        return new ResponseEntity<>(approvalInfos, HttpStatus.OK);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<OrderApprovalInfo>> updateOrderApprovalInfo(
            @PathVariable Integer id,
            @RequestBody OrderApprovalInfoRequest request) {
        Optional<OrderApprovalInfo> orderApprovalInfo = service.updateOrderApprovalInfo(id, request);
        return new ResponseEntity<Optional<OrderApprovalInfo>>(orderApprovalInfo, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<OrderApprovalInfo>> updateOrderApprovalInfoStatus(
            @PathVariable Integer id,
            @RequestBody OrderApprovalInfoRequest request) {
        Optional<OrderApprovalInfo> receivedProduct = service.updateOrderApprovalInfoStatus(id,
                request);
        return new ResponseEntity<Optional<OrderApprovalInfo>>(receivedProduct, HttpStatus.OK);
    }

    // Delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderApprovalInfoById(@PathVariable("id") Integer id) {
        service.deleteOrderApprovalInfoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderApprovalInfo> findOrderApprovalInfoById(
            @PathVariable("id") Integer id) {
        OrderApprovalInfo orderApprovalInfo = service.findOrderApprovalInfoById(id);
        return new ResponseEntity<>(orderApprovalInfo, HttpStatus.OK);
    }

    @GetMapping("/getPaginated/{offset}/{pageSize}")
    private ApiResponse<Page<OrderApprovalInfo>> findOrderApprovalInfoByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<OrderApprovalInfo> approvalInfos = service.findOrderApprovalInfoByPagination(offset,
                pageSize);
        return new ApiResponse(approvalInfos.getSize(), approvalInfos);
    }

    @GetMapping("/getSortedPaginated/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<OrderApprovalInfo>> findSortedOrderApprovalInfoByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<OrderApprovalInfo> approvalInfos = service
                .findSortedOrderApprovalInfoByPagination(offset, pageSize, field);
        return new ApiResponse(approvalInfos.getSize(), approvalInfos);
    }

}
