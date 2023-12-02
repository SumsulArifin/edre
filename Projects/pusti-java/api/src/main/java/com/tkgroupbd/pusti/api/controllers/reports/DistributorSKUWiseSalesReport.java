// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.services.inventory.ProductStockInServiceImpl;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @Tag(name = "SKU Wise Sales Report ")
// @RequestMapping("/distributorSKUWiseSalesReport")
// public class DistributorSKUWiseSalesReport {

//     @Autowired
//     ProductStockInServiceImpl productStockInService;

//     @GetMapping("/getBySKU/{distributor_id}")
//     public ResponseEntity<List<Object[]>> getSKUByDistributorId(
//             @PathVariable("distributor_id") Integer distributor_id) {
//         List<Object[]> skuByDistributor_id = productStockInService.findSKUByDistributor_id(distributor_id);
//         return new ResponseEntity<>(skuByDistributor_id, HttpStatus.OK);
//     }

//     @GetMapping("/getBySKU/{distributor_id}/by_date")
//     public ResponseEntity<List<Object[]>> getSKUByDistributorIdWithDate(
//             @PathVariable("distributor_id") int distributor_id,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//         List<Object[]> skuByDistributor_id = productStockInService.findSKUByDistributor_idWithDate(distributor_id,
//                 startDate, endDate);
//         return ResponseEntity.ok(skuByDistributor_id);
//     }

// }
