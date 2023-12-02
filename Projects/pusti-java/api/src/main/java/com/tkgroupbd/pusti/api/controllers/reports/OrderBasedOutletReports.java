// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;
// import com.tkgroupbd.pusti.api.services.salesorders.SalesOrderService;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @Tag(name = "Outlet Based Report")
// @RequestMapping("/orderBasedOutletReport")
// public class OrderBasedOutletReports {
// @Autowired
// SalesOrderService salesOrderService;

// @GetMapping("/{outletId}/orderStatus")
// public ResponseEntity<List<SalesOrder>> findSalesReportByOutletId(
// @PathVariable("outletId") int outletId,
// @RequestParam boolean orderStatus,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SalesOrder> salesOrders =
// salesOrderService.findOrderStatusByOutletId(outletId, orderStatus, startDate,
// endDate);
// return ResponseEntity.ok(salesOrders);
// }

// @GetMapping("/maxOutletId")
// public Integer findMaxOutletId() {
// int salesOrders = salesOrderService.findMaxOutletId();
// return salesOrders;
// }

// @GetMapping("/minOutletId")
// public Integer findMinOutletId() {
// int salesOrders = salesOrderService.findMinOutletId();
// return salesOrders;
// }

// @GetMapping("/maxDesc/date")
// public List<Object[]> orderMostOutlet(@RequestParam @DateTimeFormat(pattern =
// "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<Object[]> outlet = salesOrderService.orderMostOutlet(startDate,
// endDate);
// return outlet;
// }

// }
