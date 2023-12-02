// package com.tkgroupbd.pusti.api.controllers.reports;

// import
// com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesTarget;
// import
// com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SoWorkingDay;
// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;
// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;
// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import
// com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.SoWorkingDayRepository;
// import
// com.tkgroupbd.pusti.api.services.mastersettings.places.SalesTargetServicesImpl;
// import
// com.tkgroupbd.pusti.api.services.salesorders.SalesOrderDetailsServiceImpl;
// import com.tkgroupbd.pusti.api.services.salesorders.SalesOrderService;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.DeliveredProductServiceImpl;
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
// @Tag(name = "General Report")
// @RequestMapping("/generalReports")
// public class GeneralReports {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SalesOrderDetailsServiceImpl salesOrderDetailsService;
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// SalesOrderService salesOrderService;
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// DeliveredProductServiceImpl deliveredProductService;
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// SoWorkingDayRepository soWorkingDayRepository;
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// SalesTargetServicesImpl salesTargetServices;

// @GetMapping("/getOrderDetails/by-date")
// public ResponseEntity<List<SalesOrderDetails>> getOrderDetailsBetweenDate(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SalesOrderDetails> salesOrdersOrderDetails =
// salesOrderDetailsService.salesOrderDetailsBetween(startDate,
// endDate);
// return new ResponseEntity<>(salesOrdersOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/getSalesOrderByRoute/{routeId}")
// public ResponseEntity<List<SalesOrder>>
// getSalesOrderByRouteId(@PathVariable("routeId") Integer routeId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(routeId);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getBySalesOfficer/{assignedSoId}")
// public ResponseEntity<List<SalesOrder>> getSalesOrderBySalesOfficerId(
// @PathVariable("assignedSoId") Integer assignedSoId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(assignedSoId);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getByRegion/{regionId}")
// public ResponseEntity<List<SalesOrder>>
// getSalesOrderByRegionId(@PathVariable("regionId") Integer regionId) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(regionId);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getByDistributor/{distributor_id}")
// public ResponseEntity<List<SalesOrder>> getSalesOrderByDistributorId(
// @PathVariable("distributor_id") Integer distributor_id) {
// List<SalesOrder> salesOrders =
// salesOrderService.findAllSalesOrderByZoneId(distributor_id);
// return new ResponseEntity<>(salesOrders, HttpStatus.OK);
// }

// @GetMapping("/getDeliveredStatus/by-date")
// public ResponseEntity<List<DeliveredProduct>> getDeliveredStatusBetweenDate(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<DeliveredProduct> deliveredProducts = deliveredProductService
// .getDeliveryStatusByCreatedAtBetween(startDate, endDate);
// return new ResponseEntity<>(deliveredProducts, HttpStatus.OK);
// }

// @GetMapping("/getSo/{assignedId}/by-date")
// public ResponseEntity<List<SoWorkingDay>>
// getSoStatusBetweenDate(@PathVariable("assignedId") Integer assignedId,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SoWorkingDay> soWorkingDays = soWorkingDayRepository
// .findSoWorkingDayByAssignedSalesOfficer_AssignedIdAndCreatedAtBetween(assignedId,
// startDate, endDate);
// return new ResponseEntity<>(soWorkingDays, HttpStatus.OK);
// }

// @GetMapping("/last-seven-days/reports/route_wise")
// public ResponseEntity<List<SalesOrderDetails>> getLastSevenDaysByRoute(
// @RequestParam("routeId") int routeId) {
// List<SalesOrderDetails> salesOrderDetails = salesOrderDetailsService
// .salesOrderDetailsByRouteLastManualDays(routeId);
// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/getSoKPIByRouteId/{routeId}")
// public ResponseEntity<List<SalesTarget>> getSoKPIByRouteId(
// @PathVariable("routeId") Integer routeId) {
// List<SalesTarget> salesTargets =
// salesTargetServices.getKPIByRouteId(routeId);
// return new ResponseEntity<>(salesTargets, HttpStatus.OK);
// }

// @GetMapping("/getSalesOrderDetailsByCategoryIdAndDate/{categoryId}/by-date")
// public ResponseEntity<List<SalesOrderDetails>>
// getSalesOrderDetailsByCategoryIdAndDate(
// @PathVariable("categoryId") Integer categoryId,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SalesOrderDetails> salesOrderDetails = salesOrderDetailsService
// .getSalesOrderDetailsByCategoryIdAndDate(categoryId, startDate, endDate);

// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }
// }
