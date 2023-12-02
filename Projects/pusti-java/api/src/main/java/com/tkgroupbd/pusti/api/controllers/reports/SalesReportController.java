// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;
// import com.tkgroupbd.pusti.api.services.salesorders.SalesOrderService;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @Tag(name = "Sales Report")
// @RequestMapping("salesReport")
// public class SalesReportController {

// @Autowired
// SalesOrderService salesOrderService;

// @GetMapping("/getByZoneId/{zoneId}")
// public ResponseEntity<List<SalesOrder>>
// getSalesOrderByZoneId(@PathVariable("zoneId") Integer zoneId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(zoneId);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
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

// @GetMapping("/getByDistributor/{distributor_id}")
// public ResponseEntity<List<SalesOrder>> getSalesOrderByDistributorId(
// @PathVariable("distributor_id") Integer distributor_id) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(distributor_id);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getBySalesOfficer/{assignedSoId}")

// public ResponseEntity<List<SalesOrder>> getSalesOrderBySalesOfficerId(
// @PathVariable("assignedSoId") Integer assignedSoId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(assignedSoId);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// // @GetMapping("/getByRegion/{regionId}")
// // public ResponseEntity<List<SalesOrder>>
// // getSalesOrderByRegionId(@PathVariable("regionId") Integer regionId) {
// // List<SalesOrder> salesOrders =
// // salesOrderService.findAllSalesOrderByZoneId(regionId);
// // }

// // public ResponseEntity<List<SalesOrder>>
// // getSalesOrderBySalesOfficerId(@PathVariable("assignedSoId") Integer
// // assignedSoId) {
// // List<SalesOrder> salesOrders =
// // salesOrderService.findAllSalesOrderBySalesOfficerId(assignedSoId);

// // return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// // }

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

// @GetMapping("/monthly-report/all")
// public ResponseEntity<List<SalesOrder>> findByMonthlyBetween(
// @RequestParam int year,
// @RequestParam int month) {
// List<SalesOrder> salesOrders = salesOrderService.findByMonthlyBetween(year,
// month);
// return ResponseEntity.ok(salesOrders);
// }

// @GetMapping("/monthly-report/by/so")
// public List<Object[]> getCategoriesByCreatedAtAndRouteWise(
// @RequestParam int year,
// @RequestParam int month,
// @RequestParam int id) {
// return salesOrderService.getByCreatedAtAndRouteWise(year, month, id);
// }

// @GetMapping("/createdAt-date/by/category/{id}")
// public List<Object[]> getCategoriesByCreatedAtAndRouteWise(
// @PathVariable("id") int id) {
// return salesOrderService.achievementDateByCategory(id);
// }

// @GetMapping("/sales/by/category/{id}")
// public List<Object[]> getSalesAchievementByCategory(
// @PathVariable("id") int id) {
// return salesOrderService.salesAchievementByCategory(id);
// }

// @GetMapping("/find/RouteAndOutlet/ByCreatedAt")
// public ResponseEntity<List<Object[]>> findRouteAndOutletByCreatedAt(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date

// ) {
// List<Object[]> salesOrders =
// salesOrderService.findRouteAndOutletByCreatedAt(date);
// return ResponseEntity.ok(salesOrders);
// }
// }
