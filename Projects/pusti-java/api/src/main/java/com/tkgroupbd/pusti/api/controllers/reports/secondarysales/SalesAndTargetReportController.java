// package com.tkgroupbd.pusti.api.controllers.reports.secondarysales;

// import
// com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesTarget;
// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;
// import
// com.tkgroupbd.pusti.api.services.mastersettings.places.SalesTargetServicesImpl;
// import
// com.tkgroupbd.pusti.api.services.salesorders.SalesOrderDetailsServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Tag(name = "SalesAndTargetReport")
// @RestController
// @RequestMapping("/salesAndTargetReport")
// public class SalesAndTargetReportController {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SalesTargetServicesImpl salesTargetService;

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SalesOrderDetailsServiceImpl salesOrderDetailsService;

// /**
// * SKU wise monthly target vs. sales report.
// */
// @GetMapping("/get/salesOrderDetail/monthly/BySku")
// public ResponseEntity<List<SalesOrderDetails>> getSalesOrderDetailsBySku(
// @RequestParam("sku") String sku,
// @RequestParam("year") int year,
// @RequestParam("month") int month) {
// List<SalesOrderDetails> salesOrderDetails =
// salesOrderDetailsService.salesOrderDetailsBySku(sku, year, month);
// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }

// @GetMapping("/get/salesTarget/monthly/BySku")
// public ResponseEntity<List<SalesTarget>> getSalesTargetBySku(
// @RequestParam("sku") String sku,
// @RequestParam("year") int year,
// @RequestParam("month") int month) {
// List<SalesTarget> salesTargets =
// salesTargetService.salesOrderDetailsBySku(sku, year, month);
// return new ResponseEntity<>(salesTargets, HttpStatus.OK);
// }

// @GetMapping("/get/salesTarget/BySku")
// public ResponseEntity<List<SalesTarget>> getSalesTargetBySku(
// @RequestParam("sku") String sku

// ) {
// List<SalesTarget> salesTargets =
// salesTargetService.salesOrderDetailsBySku(sku);
// return new ResponseEntity<>(salesTargets, HttpStatus.OK);
// }

// }
