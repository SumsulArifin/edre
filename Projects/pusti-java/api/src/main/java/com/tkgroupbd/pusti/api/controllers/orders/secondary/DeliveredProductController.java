package com.tkgroupbd.pusti.api.controllers.orders.secondary;
// package com.tkgroupbd.pusti.api.controllers.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.DeliveredProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.DeliveredProductServiceImpl;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @Tag(name = "Delivered Product")
// @RestController
// @RequestMapping("/deliveredProduct")
// public class DeliveredProductController {
// @Autowired
// private DeliveredProductServiceImpl deliveredProductService;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> addDeliveredProduct(@RequestBody
// DeliveredProductRequest request) {
// MessageResponse deliveredProduct =
// deliveredProductService.createDeliveredProduct(request);
// return new ResponseEntity<>(deliveredProduct, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<DeliveredProduct>>
// updateDeliveredProduct(@PathVariable Integer id,
// @RequestBody DeliveredProductRequest request) {
// Optional<DeliveredProduct> salesOrderRemark =
// deliveredProductService.updateDeliveredProduct(id, request);
// return new ResponseEntity<Optional<DeliveredProduct>>(salesOrderRemark,
// HttpStatus.OK);
// }

// }
