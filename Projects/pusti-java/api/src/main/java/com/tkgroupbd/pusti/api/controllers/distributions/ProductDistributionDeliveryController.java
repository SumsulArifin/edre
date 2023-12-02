// package com.tkgroupbd.pusti.api.controllers.distributions;

// import
// com.tkgroupbd.pusti.api.data.models.entity.distributions.ProductDistributionDelivery;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.distributions.ProductDistributionDeliveryRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.distributions.ProductDistributionDeliveryServiceImpl;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @Tag(name = "Product Distribution Delivery")
// @RestController
// @RequestMapping("/productDistributionDelivery")
// public class ProductDistributionDeliveryController {

// @Autowired
// private ProductDistributionDeliveryServiceImpl service;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> createDistributionDelivery(
// @RequestBody ProductDistributionDeliveryRequest request) {
// MessageResponse distributionDelivery =
// service.createDeleveryAndUpdateStockInDistributionDelivery(request,
// request.getSku(), request.getQuantity());
// return new ResponseEntity<>(distributionDelivery, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<ProductDistributionDelivery>>
// updateProductDistributionDelivery(
// @PathVariable Integer id,
// @RequestBody ProductDistributionDeliveryRequest request) {
// Optional<ProductDistributionDelivery> offerType =
// service.updateProductDistributionDelivery(id, request);
// return new ResponseEntity<Optional<ProductDistributionDelivery>>(offerType,
// HttpStatus.OK);
// }

// @PutMapping("/statusChange/{id}")
// public ResponseEntity<Optional<ProductDistributionDelivery>>
// statusChangeProductDistributionDelivery(
// @PathVariable Integer id,
// @RequestBody ProductDistributionDeliveryRequest request) {
// Optional<ProductDistributionDelivery> offerType =
// service.statusChangeProductDistributionDelivery(id, request);
// return new ResponseEntity<Optional<ProductDistributionDelivery>>(offerType,
// HttpStatus.OK);
// }

// }
