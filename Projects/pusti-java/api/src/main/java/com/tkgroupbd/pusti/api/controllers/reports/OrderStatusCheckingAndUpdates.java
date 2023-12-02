// package com.tkgroupbd.pusti.api.controllers.reports;

// import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
// import com.tkgroupbd.pusti.api.services.inventory.ProductStockInServiceImpl;
// import com.tkgroupbd.pusti.api.services.primaryorders.ApprovePrimaryOrdersServiceImpl;
// import com.tkgroupbd.pusti.api.services.secondaryorders.DeliveredProductService;
// import com.tkgroupbd.pusti.api.utils.constant.Message;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @Tag(name = "Order Status Management")
// @RequestMapping("/orderStatusCheckingAndUpdates")
// public class OrderStatusCheckingAndUpdates {
//     @Autowired
//     @SuppressWarnings(Message.AUTOWIRED_OK)
//     ProductStockInServiceImpl productStockInService;
//     @Autowired
//     @SuppressWarnings(Message.AUTOWIRED_OK)
//     DeliveredProductService deliveredProductService;
//     @Autowired
//     @SuppressWarnings(Message.AUTOWIRED_OK)
//     ApprovePrimaryOrdersServiceImpl approvePrimaryOrdersService;

//     @GetMapping("/getStockBy/{distributor_id}/by_date")
//     public ResponseEntity<List<Object[]>> getStockByDistributorIdWithDate(
//             @PathVariable("distributor_id") int distributor_id,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//         List<Object[]> skuByDistributor_id = productStockInService.findStockyDistributor_idWithDate(distributor_id,
//                 startDate, endDate);
//         return ResponseEntity.ok(skuByDistributor_id);
//     }

//     @GetMapping("/delivererProduct/{distributor_id}")
//     public ResponseEntity<List<Object[]>> getDeliveredProductByDistributorId(
//             @PathVariable("distributor_id") int distributor_id) {
//         List<Object[]> skuByDistributor_id = deliveredProductService.getDeliveredProductByDistributorId(distributor_id);
//         return ResponseEntity.ok(skuByDistributor_id);
//     }

//     @GetMapping("/approvePrimaryOrders/byDate")
//     public ResponseEntity<List<Object[]>> getApprovePrimaryOrdersByCurrentDate(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//         List<Object[]> skuByDistributor_id = approvePrimaryOrdersService.getApprovePrimaryOrdersByCurrentDate(startDate,
//                 endDate);
//         return ResponseEntity.ok(skuByDistributor_id);

//     }

//     @GetMapping("/getWeeklyDeliveredReport/by-date")
//     public ResponseEntity<List<Object[]>> getWeeklyDeliveredReport(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, DeliveryStatus deliveryStatus) {
//         List<Object[]> pendingProductList = deliveredProductService.getdWeeklyDeliveredReport(deliveryStatus, startDate,
//                 endDate);
//         return ResponseEntity.ok(pendingProductList);
//     }

// }
