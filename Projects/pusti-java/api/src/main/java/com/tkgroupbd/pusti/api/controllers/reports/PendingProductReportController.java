// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.PendingProduct;
// import com.tkgroupbd.pusti.api.services.secondaryorders.PendingProductServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @Tag(name = "Pending Product Report")
// @RestController
// @RequestMapping("/pendingProductReport")
// public class PendingProductReportController {
//     @Autowired
//     @SuppressWarnings(Message.AUTOWIRED_OK)
//     private PendingProductServiceImpl service;

//     @GetMapping("/by-date")
//     public List<PendingProduct> getPendingProductsByDate() {
//         return service.getPendingProductsByDate();
//     }

//     @GetMapping("/by-date/between")
//     public ResponseEntity<List<PendingProduct>> getPendingProductByDateBetween(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//         List<PendingProduct> pendingProductList = service.getPendingProductsByDateBetween(startDate, endDate);
//         return ResponseEntity.ok(pendingProductList);
//     }

//     /**
//      * http://localhost:8080/pendingProductReport/by_date/sku?startDate=2023-07-16&sku=string
//      * 
//      * @param startDate
//      * @param sku
//      * @return
//      */
//     @GetMapping("/by-date/sku")
//     public ResponseEntity<List<PendingProduct>> getPendingProductByDateAndSku(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam String sku) {
//         List<PendingProduct> pendingProductList = service.findPendingProductByCreatedAtAndSku(startDate, sku);
//         return ResponseEntity.ok(pendingProductList);
//     }

//     /**
//      * Interval base pending product search
//      * http://localhost:8080/pendingProductReport/by_date/previousDay?date=2023-08-16&howMany=15
//      * date = any date(YYYY-MM-dd) format
//      * howMany = how many days need to see records
//      * 
//      * @param date
//      * @param howMany
//      * @return
//      */
//     @GetMapping("/by-date/previousDay")
//     public ResponseEntity<List<PendingProduct>> getPendingProductByDateBetweenPreviousDay(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam int howMany) {
//         List<PendingProduct> pendingProductList = service.getByCreatedAtBetweenLastManualDays(date, howMany);
//         return ResponseEntity.ok(pendingProductList);
//     }

//     @GetMapping("/byOrderId/{id}")
//     public ResponseEntity<List<PendingProduct>> getByApproveOrderId(
//             @PathVariable("id") int id) {
//         List<PendingProduct> pendingProductList = service.getAllPendingProductsByOrderId(id);
//         return ResponseEntity.ok(pendingProductList);
//     }

//     @GetMapping("/all")
//     public ResponseEntity<List<PendingProduct>> getAllPendingProduct() {
//         List<PendingProduct> pendingProductList = service.getAllPendingProducts();
//         return ResponseEntity.ok(pendingProductList);
//     }

// }
