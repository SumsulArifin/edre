package com.tkgroupbd.pusti.api.controllers.orders.secondary;
// package com.tkgroupbd.pusti.api.controllers.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.PendingProduct;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.PendingProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.PendingProductServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.Optional;

// @RestController
// @Tag(name = "Pending Product")
// @RequestMapping("/pendingProduct")
// public class PendingProductController {
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private PendingProductServiceImpl service;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> addPendingProduct(@RequestBody
// PendingProductRequest request) {
// MessageResponse messageResponse = service.createPendingProduct(request);
// return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
// }

// /**
// * firstly find all pending product that status is false
// * secondly add data in delevery product table
// *
// * @return
// */
// @PostMapping("/add/pending/to/delevery")
// public ResponseEntity<MessageResponse> addPendingProductDelevery() {
// MessageResponse messageResponse = service.addAllInDeliveredFormPending();
// return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<PendingProduct>>
// updatePendingProducts(@PathVariable Integer id,
// @RequestBody PendingProductRequest request) {
// Optional<PendingProduct> pendingProduct = service.updatePendingProduct(id,
// request);
// return new ResponseEntity<Optional<PendingProduct>>(pendingProduct,
// HttpStatus.OK);
// }

// @PutMapping("/update/status/{id}")
// public ResponseEntity<Optional<PendingProduct>>
// updatePendingProductStatus(@PathVariable Integer id,
// @RequestBody PendingProductRequest request) {
// Optional<PendingProduct> pendingProduct =
// service.updatePendingProductStatus(id, request);
// return new ResponseEntity<Optional<PendingProduct>>(pendingProduct,
// HttpStatus.OK);
// }

// /**
// * delivery add while pending product added or update
// * if partially added then pending will be updated as
// * previous pending product - delivered product = update pending product
// *
// * @param id
// * @param request
// * @return
// */
// @PutMapping("/delivery/add/pending/update/{id}")
// public ResponseEntity<MessageResponse>
// updatePendingProductQuantityAndAddToDeleveryProduct(@PathVariable Integer id,
// @RequestBody PendingProductRequest request) {
// MessageResponse messageResponse =
// service.updatePendingProductQuantityAndAddToDeleveryProduct(id, request);
// return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
// }

// }
