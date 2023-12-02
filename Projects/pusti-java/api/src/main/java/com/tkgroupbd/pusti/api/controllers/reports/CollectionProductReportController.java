// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.CollectionProduct;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.CollectionProductServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @Tag(name = "Collection Product")
// @RestController
// @RequestMapping("/collectionProduct")
// public class CollectionProductReportController {
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private CollectionProductServiceImpl collectionProductService;

// @GetMapping("/getAll")
// public ResponseEntity<List<CollectionProduct>> getAllCollectionProduct() {
// List<CollectionProduct> collectionProducts =
// collectionProductService.getAllCollectionProduct();
// return new ResponseEntity<>(collectionProducts, HttpStatus.OK);
// }

// @GetMapping("/getById/{id}")
// public CollectionProduct getCollectionProductById(@PathVariable("id") int id)
// {
// return collectionProductService.getCollectionProductById(id);
// }

// @GetMapping("/getAllBy/so/{id}")
// public List<CollectionProduct> getCollectionProductBySoId(@PathVariable("id")
// int id) {
// return collectionProductService.getCollectionProductBySoId(id);
// }

// @GetMapping("/getAllBy/outlet/{id}")
// public List<CollectionProduct>
// getCollectionProductByOutletAmendmentId(@PathVariable("id") int id) {
// return collectionProductService.getCollectionProductByOutletAmendmentId(id);
// }

// /**
// * Current date wise report
// *
// * @return
// */
// @GetMapping("/by-date")
// public List<CollectionProduct> getCollectionProductByDate() {
// return collectionProductService.getCollectionProductByDate();
// }

// @GetMapping("/by-date/sku")
// public ResponseEntity<List<CollectionProduct>> getPendingProductByDateAndSku(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt,
// @RequestParam String sku) {
// List<CollectionProduct> collectionProducts = collectionProductService
// .findCollectionProductByCreatedAtAndSku(createdAt, sku);
// return ResponseEntity.ok(collectionProducts);
// }

// @GetMapping("/by-date/between")
// public ResponseEntity<List<CollectionProduct>>
// getCollectionProductByDateBetween(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
// List<CollectionProduct> collectionProducts = collectionProductService
// .getCollectionProductByDateBetween(startDate, endDate);
// return ResponseEntity.ok(collectionProducts);
// }

// @GetMapping("/by-date/any")
// public ResponseEntity<List<CollectionProduct>> getCollectionProductByAnyDate(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
// List<CollectionProduct> collectionProducts =
// collectionProductService.getCollectionProductByAnyDate(date);
// return ResponseEntity.ok(collectionProducts);
// }

// /**
// *
// http://localhost:8080/pendingProduct/by_date/previousDay?date=2023-08-16&howMany=15
// * date = any date(YYYY-MM-dd) format
// * howMany = how many days need to see records
// *
// * @param date
// * @param howMany
// * @return
// */
// @GetMapping("/by-date/previousDay")
// public ResponseEntity<List<CollectionProduct>>
// getByCreatedAtBetweenLastManualDays(
// @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
// @RequestParam int howMany) {
// List<CollectionProduct> collectionProducts =
// collectionProductService.getByCreatedAtBetweenLastManualDays(date,
// howMany);
// return ResponseEntity.ok(collectionProducts);
// }

// /**
// * http://localhost:8080/collectionProduct/getPaginated/0/1
// *
// * @param offset
// * @param pageSize
// * @return
// */
// @GetMapping("/getPaginated/{offset}/{pageSize}")
// private ApiResponse<Page<CollectionProduct>>
// findCollectionProductByPagination(@PathVariable int offset,
// @PathVariable int pageSize) {
// Page<CollectionProduct> collectionProducts =
// collectionProductService.findCollectionProductByPagination(offset,
// pageSize);
// return new ApiResponse(collectionProducts.getSize(), collectionProducts);
// }

// /**
// * http://localhost:8080/collectionProduct/getPaginated/0/1/createdAt
// *
// * @param offset
// * @param pageSize
// * @param field
// * @return
// */
// @GetMapping("/getPaginated/{offset}/{pageSize}/{field}")
// private ApiResponse<Page<CollectionProduct>>
// findSortedCollectionProductByPagination(
// @PathVariable int offset,
// @PathVariable int pageSize,
// @PathVariable String field) {
// Page<CollectionProduct> collectionProducts = collectionProductService
// .findSortedCollectionProductByPagination(offset, pageSize, field);
// return new ApiResponse(collectionProducts.getSize(), collectionProducts);
// }

// /**
// * http://localhost:8080/collectionProduct/monthly-report?year=2023&month=8
// *
// * @param year
// * @param month
// * @return
// */

// @GetMapping("/monthly-report")
// public ResponseEntity<List<CollectionProduct>> getMonthlyReport(
// @RequestParam int year,
// @RequestParam int month) {
// List<CollectionProduct> monthlyReport =
// collectionProductService.findCollectionProductByMonthly(year, month);
// return ResponseEntity.ok(monthlyReport);
// }

// /**
// *
// http://localhost:8080/collectionProduct/monthly-report/soWise?year=2023&month=8&soId=2
// *
// * @param year
// * @param month
// * @param soId
// * @return
// */
// @GetMapping("/monthly-report/soWise")
// public ResponseEntity<List<CollectionProduct>>
// findByCreatedAtBetweenAndAssignedSalesOfficer(
// @RequestParam int year,
// @RequestParam int month,
// @RequestParam int soId) {
// List<CollectionProduct> monthlyReport = collectionProductService
// .findByCreatedAtBetweenAndAssignedSalesOfficer(year, month, soId);
// return ResponseEntity.ok(monthlyReport);
// }

// @GetMapping("/by_zone_id")
// public ResponseEntity<List<CollectionProduct>> getCollectionProductsByZone(
// @RequestParam("zoneId") int zoneId) {
// List<CollectionProduct> collectionProducts =
// collectionProductService.getCollectionProductsByZoneId(zoneId);
// return new ResponseEntity<>(collectionProducts, HttpStatus.OK);
// }

// @GetMapping("/by_region_id")
// public ResponseEntity<List<CollectionProduct>> getCollectionProductsByRegion(
// @RequestParam("regionId") int regionId) {
// List<CollectionProduct> collectionProducts =
// collectionProductService.getCollectionProductsByZoneId(regionId);
// return new ResponseEntity<>(collectionProducts, HttpStatus.OK);
// }

// }
