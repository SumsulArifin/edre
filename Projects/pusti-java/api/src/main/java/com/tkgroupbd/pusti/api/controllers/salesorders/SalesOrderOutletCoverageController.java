// package com.tkgroupbd.pusti.api.controllers.salesorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderOutletCoverage;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderOutletCoverageRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.salesorders.SalesOrderOutletCoverageService;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @Tag(name = "Sales Order Coverage By Outlet")
// @RequestMapping("salesOrderOutletCoverage")
// public class SalesOrderOutletCoverageController {
// @Autowired
// SalesOrderOutletCoverageService salesOrderOutletCoverageService;

// @PostMapping("/add")
// private ResponseEntity<MessageResponse> saveSalesOrderOutletCoverage(
// @RequestBody SalesOrderOutletCoverageRequest salesOrderOutletCoverageRequest)
// {
// MessageResponse salesOrderOutletCoverage = salesOrderOutletCoverageService
// .createSalesOrderOutletCoverage(salesOrderOutletCoverageRequest);
// return new ResponseEntity<>(salesOrderOutletCoverage, HttpStatus.CREATED);
// }

// @GetMapping("/getByOutletId/{id}")
// public ResponseEntity<List<SalesOrderOutletCoverage>>
// getSalesOrderOutletCoveragesByOutletId(
// @PathVariable("id") Integer id) {
// List<SalesOrderOutletCoverage> salesOrderOutletCoverages =
// salesOrderOutletCoverageService
// .getSalesOrderOutletCoverageByOutletId(id);
// return new ResponseEntity<>(salesOrderOutletCoverages, HttpStatus.OK);
// }

// @GetMapping("/getById/{id}")
// public ResponseEntity<SalesOrderOutletCoverage>
// getSalesOrderOutletCoverageById(@PathVariable("id") Integer id) {
// SalesOrderOutletCoverage salesOrderOutletCoverage =
// salesOrderOutletCoverageService
// .getSalesOrderOutletCoverageById(id);
// return new ResponseEntity<>(salesOrderOutletCoverage, HttpStatus.OK);
// }

// @GetMapping("/getAll")
// public ResponseEntity<List<SalesOrderOutletCoverage>>
// getApprovePrimaryOrders() {
// List<SalesOrderOutletCoverage> salesOrderOutletCoverages =
// salesOrderOutletCoverageService
// .getAllSalesOrderOutletCoverage();
// return new ResponseEntity<>(salesOrderOutletCoverages, HttpStatus.OK);
// }

// }
