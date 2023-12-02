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
// @RequestMapping("/sOWisesSalesReport")
// @Tag(name = "Sales Report By So")

// public class SOWisesSalesReport {

// @Autowired
// SalesOrderService salesOrderService;

// @GetMapping("/{assignedSoId}/by_date")
// public ResponseEntity<List<SalesOrder>>
// findSOWiseSalesReportByTheSelectedDate(
// @PathVariable("assignedSoId") int assignedSoId,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<SalesOrder> salesOrders =
// salesOrderService.sOWiseSalesReportByTheSelectedDate(assignedSoId, startDate,
// endDate);
// return ResponseEntity.ok(salesOrders);
// }

// }
