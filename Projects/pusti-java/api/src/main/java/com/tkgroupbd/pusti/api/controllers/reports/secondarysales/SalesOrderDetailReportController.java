// package com.tkgroupbd.pusti.api.controllers.reports.secondarysales;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;
// import
// com.tkgroupbd.pusti.api.services.salesorders.SalesOrderDetailsServiceImpl;
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
// @Tag(name = "Sales Order Details")
// @RequestMapping("/salesOrderDetailsReport")
// public class SalesOrderDetailReportController {
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SalesOrderDetailsServiceImpl salesOrderDetailsService;

// @GetMapping("/by-date")
// public ResponseEntity<List<SalesOrderDetails>> getSalesOrderDetails() {
// List<SalesOrderDetails> salesOrdersOrderDetails =
// salesOrderDetailsService.salesOrderDetails();
// return new ResponseEntity<>(salesOrdersOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/by-date/between")
// public ResponseEntity<List<SalesOrderDetails>> getSalesOrderDetailsBetween(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SalesOrderDetails> salesOrdersOrderDetails =
// salesOrderDetailsService.salesOrderDetailsBetween(startDate,
// endDate);
// return new ResponseEntity<>(salesOrdersOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/all/{id}")
// public ResponseEntity<List<SalesOrderDetails>>
// salesOrderDetailsAll(@PathVariable("id") int id) {
// List<SalesOrderDetails> salesOrderDetails = salesOrderDetailsService
// .salesOrderDetailsAllByAssignedSalesOfficerId(id);

// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/all/by/soId/{id}")
// public ResponseEntity<List<SalesOrderDetails>>
// salesOrderDetailsAllByAssignedSalesOfficerId(
// @PathVariable("id") int id) {
// List<SalesOrderDetails> salesOrderDetails = salesOrderDetailsService
// .salesOrderDetailsAllByAssignedSalesOfficerId(id);
// System.out.println(salesOrderDetails);
// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/most-ordered")
// public List<Object[]> getAssignedSalesOfficerOrderedMost() {
// return salesOrderDetailsService.getAssignedSalesOfficerOrderedMost();
// }

// @GetMapping("/least-ordered")
// public List<Object[]> getAssignedSalesOfficerOrderedLeast() {
// return salesOrderDetailsService.getAssignedSalesOfficerOrderedLeast();
// }

// @GetMapping("/get/salesOrderDetails/BySku")
// public ResponseEntity<List<SalesOrderDetails>> getSalesOrderDetailsBySku(
// @RequestParam("sku") String sku) {
// List<SalesOrderDetails> salesOrderDetails =
// salesOrderDetailsService.salesOrderDetailsBySku(sku);
// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }
// }
