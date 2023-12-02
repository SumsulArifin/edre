// package com.tkgroupbd.pusti.api.controllers.salesorders;

// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import com.tkgroupbd.pusti.api.services.salesorders.SalesOrderService;
// import com.tkgroupbd.pusti.api.utils.constant.Message;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @Tag(name = "Sales Order")
// @RequestMapping("/salesOrder")
// public class SalesOrderController {
// @Autowired
// SalesOrderService salesOrderService;

// @PostMapping("/add")
// private ResponseEntity<MessageResponse> saveSalesOrderWithDetails(
// @RequestBody SalesOrderRequest salesOrderRequest) {
// MessageResponse salesOrder =
// salesOrderService.addSalesOrder(salesOrderRequest);
// return new ResponseEntity<>(salesOrder, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<MessageResponse> updateSalesOrder(@PathVariable int id,
// @RequestBody SalesOrderRequest salesOrderRequest) {
// MessageResponse response = salesOrderService.updateSalesOrder(id,
// salesOrderRequest);
// HttpStatus status = (response.getMessage().equals(Message.SUCCESS_CREATION))
// ? HttpStatus.OK
// : HttpStatus.INTERNAL_SERVER_ERROR;
// return new ResponseEntity<>(response, status);
// }

// @GetMapping("/getByOutletId/{id}")
// public ResponseEntity<List<SalesOrder>>
// getSalesOrderByOutletId(@PathVariable("id") Integer id) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByOutletId(id);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getByRouteId/{id}")
// public ResponseEntity<List<SalesOrder>>
// getSalesOrderByRouteId(@PathVariable("id") Integer id) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByRouteId(id);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/monthly-report/soWise")
// public ResponseEntity<List<SalesOrder>>
// findByCreatedAtBetweenAndAssignedSalesOfficer(
// @RequestParam int year,
// @RequestParam int month,
// @RequestParam int soId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findByCreatedAtBetweenAndAssignedSalesOfficer(year, month,
// soId);
// return ResponseEntity.ok(salesOrders);
// }

// @GetMapping("/find-sku/soWise")
// public ResponseEntity<List<SalesOrder>> getBySkyWithDateAndSoWise(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
// @RequestParam int soId) {
// List<SalesOrder> salesOrders =
// salesOrderService.getBySkyWithDateAndSoWise(date, soId);
// return ResponseEntity.ok(salesOrders);
// }

// @GetMapping("/monthly-report/all/")
// public ResponseEntity<List<SalesOrder>> findByMonthlyBetween(
// @RequestParam int year,
// @RequestParam int month) {
// List<SalesOrder> salesOrders = salesOrderService.findByMonthlyBetween(year,
// month);
// return ResponseEntity.ok(salesOrders);
// }

// }
