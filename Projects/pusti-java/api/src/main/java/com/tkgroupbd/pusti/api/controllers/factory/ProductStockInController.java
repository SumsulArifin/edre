package com.tkgroupbd.pusti.api.controllers.factory;
// package com.tkgroupbd.pusti.api.controllers.inventory;

// import com.tkgroupbd.pusti.api.data.models.entity.inventory.ProductStockIn;
// import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.inventory.ProductStockInRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import com.tkgroupbd.pusti.api.services.inventory.ProductStockInServiceImpl;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @Tag(name = "Inventory")
// @RestController
// @RequestMapping("/productStockIn")
// public class ProductStockInController {
// @Autowired
// private ProductStockInServiceImpl service;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> createCollectProduct(@RequestBody
// ProductStockInRequest request) {
// MessageResponse newProductStockIn = service.createCollectProduct(request);
// return new ResponseEntity<>(newProductStockIn, HttpStatus.CREATED);
// }

// @PutMapping("/update/{pId}")
// public ResponseEntity<Optional<ProductStockIn>>
// updateProductStockIn(@PathVariable Integer pId,
// @RequestBody ProductStockInRequest request) {
// Optional<ProductStockIn> updateProductStockIn = service.updateProduct(pId,
// request);
// return new ResponseEntity<Optional<ProductStockIn>>(updateProductStockIn,
// HttpStatus.OK);
// }

// /**
// * get All product from pacific date
// *
// * @param createdAt
// * @return
// */

// @GetMapping("/by-created-at")
// public List<ProductStockIn> getByCreatedAt(
// @RequestParam String createdAt) {
// return service.getByCreatedAt(createdAt);
// }

// /**
// * get all product as descending which createdAt column
// *
// * @return
// */
// @GetMapping("/all-by-date-desc")
// public List<ProductStockIn> getAllByCreationDateDesc() {
// return service.getAllByCreationDateDesc();
// }

// @GetMapping("/received/from/{receivedFrom}")
// public ResponseEntity<List<ProductStockIn>> findByReceivedFrom(@PathVariable
// ReceivedFrom receivedFrom) {
// List<ProductStockIn> productStockInList =
// service.findByReceivedFrom(receivedFrom);
// return ResponseEntity.ok(productStockInList);
// }

// }
