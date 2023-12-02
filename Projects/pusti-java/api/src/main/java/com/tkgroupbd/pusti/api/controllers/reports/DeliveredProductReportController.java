// package com.tkgroupbd.pusti.api.controllers.reports;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.DeliveredProductServiceImpl;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @Tag(name = "Delivered Product Report")
// @RestController
// @RequestMapping("/deliveredProductReport")
// public class DeliveredProductReportController {
// @Autowired
// private DeliveredProductServiceImpl deliveredProductService;

// @GetMapping("/all")
// public ResponseEntity<List<DeliveredProduct>> getAllDeliveredProduct() {
// List<DeliveredProduct> deliveredProduct =
// deliveredProductService.getAllDeliveredProduct();
// return new ResponseEntity<>(deliveredProduct, HttpStatus.OK);
// }

// @GetMapping("/getById/{id}")
// public ResponseEntity<DeliveredProduct>
// getSalesOrderRemarkById(@PathVariable("id") Integer id) {
// DeliveredProduct deliveredProduct =
// deliveredProductService.getDeliveredProductById(id);
// return new ResponseEntity<>(deliveredProduct, HttpStatus.OK);
// }

// /**
// *
// http://localhost:8080/deliveredProduct/date/between?startDate=2023-07-19&endDate=2023-08-21
// *
// * @param startDate
// * @param endDate
// * @return
// */
// @GetMapping("/date/between")
// public ResponseEntity<List<DeliveredProduct>> searchDeliveredProductByDate(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<DeliveredProduct> deliveredProducts =
// deliveredProductService.getAllByDateRange(startDate, endDate);
// return ResponseEntity.ok(deliveredProducts);
// }

// @GetMapping("/byCreatedAt")
// public ResponseEntity<List<DeliveredProduct>> getProductsByCreatedAt(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
// List<DeliveredProduct> products =
// deliveredProductService.findByCreatedAt(createdAt);
// return ResponseEntity.ok(products);
// }

// @GetMapping("/monthly-report")
// public ResponseEntity<List<DeliveredProduct>> getMonthlyReport(
// @RequestParam int year,
// @RequestParam int month) {
// List<DeliveredProduct> monthlyReport =
// deliveredProductService.findDeliveredProductByMonthly(year, month);
// return ResponseEntity.ok(monthlyReport);
// }

// @GetMapping("/monthly-report-by-distributor")
// public ResponseEntity<List<DeliveredProduct>> getMonthlyReportDistributorId(
// @RequestParam int year,
// @RequestParam int month,
// @RequestParam int id) {
// List<DeliveredProduct> monthlyReport = deliveredProductService
// .findDeliveredProductByMonthlyByDistributorId(year, month, id);
// return ResponseEntity.ok(monthlyReport);
// }

// @GetMapping("/by-date/sku")
// public ResponseEntity<List<DeliveredProduct>>
// getDeliveredProductByDateAndSku(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam String sku) {
// List<DeliveredProduct> deliveredProducts = deliveredProductService
// .findDeliveredProductByCreatedAtAndSku(startDate, sku);
// return ResponseEntity.ok(deliveredProducts);
// }

// @GetMapping("/category/total")
// public String getAllCategory() {
// String a = deliveredProductService.findAllRatioCategory();
// System.out.println(a);
// return a;

// }

// @GetMapping("/find/DeliveredProduct/By/Category/total/{id}")
// public List<Object[]> findDeliveredProductByCategory(@PathVariable("id") int
// id) {
// return deliveredProductService.findDeliveredProductByCategory(id);
// }

// @GetMapping("/find/daily/received/product/{id}")
// public ResponseEntity<List<DeliveredProduct>>
// getDbDailyReceivedProduct(@PathVariable("id") Integer id) {
// List<DeliveredProduct> deliveredProduct =
// deliveredProductService.getDbDailyReceivedProduct(id);
// return new ResponseEntity<>(deliveredProduct, HttpStatus.OK);
// }
// }
