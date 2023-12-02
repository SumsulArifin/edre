// package com.tkgroupbd.pusti.api.controllers.salesorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderRemark;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderRemarkRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.salesorders.SalesOrderRemarkServiceImpl;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @Tag(name = "Sales Order Remark")
// @RestController
// @RequestMapping("/salesOrderRemark")
// public class SalesOrderRemarkController {

// @Autowired
// private SalesOrderRemarkServiceImpl service;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> addSalesOrderRemark(@RequestBody
// SalesOrderRemarkRequest request) {
// MessageResponse salesOrderRemark = service.createSalesOrderRemark(request);
// return new ResponseEntity<>(salesOrderRemark, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<SalesOrderRemark>>
// updateSalesOrderRemark(@PathVariable Integer id,
// @RequestBody SalesOrderRemarkRequest request) {
// Optional<SalesOrderRemark> salesOrderRemark =
// service.updateSalesOrderRemark(id, request);
// return new ResponseEntity<Optional<SalesOrderRemark>>(salesOrderRemark,
// HttpStatus.OK);
// }

// @PutMapping("/statusChange/{id}")
// public ResponseEntity<Optional<SalesOrderRemark>>
// salesOrderRemarkStatusChange(@PathVariable Integer id,
// @RequestBody SalesOrderRemarkRequest request) {
// Optional<SalesOrderRemark> salesOrderRemark =
// service.salesOrderRemarkStatusChange(id, request);
// return new ResponseEntity<Optional<SalesOrderRemark>>(salesOrderRemark,
// HttpStatus.OK);
// }

// @GetMapping("/all")
// public ResponseEntity<List<SalesOrderRemark>> getAllSalesOrderRemark() {
// List<SalesOrderRemark> salesOrderRemarks = service.getAllSalesOrderRemark();
// return new ResponseEntity<>(salesOrderRemarks, HttpStatus.OK);
// }

// @GetMapping("/getById/{id}")
// public ResponseEntity<SalesOrderRemark>
// getSalesOrderRemarkById(@PathVariable("id") Integer id) {
// SalesOrderRemark salesOrderRemarks = service.getSalesOrderRemarkById(id);
// return new ResponseEntity<>(salesOrderRemarks, HttpStatus.OK);
// }

// @DeleteMapping("/delete/{id}")
// public ResponseEntity<?> deleteSalesOrderRemarkById(@PathVariable("id")
// Integer id) {
// service.deleteSalesOrderRemark(id);
// return new ResponseEntity<>(HttpStatus.OK);
// }
// }
