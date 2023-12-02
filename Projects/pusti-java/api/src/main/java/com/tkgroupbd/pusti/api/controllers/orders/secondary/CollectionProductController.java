package com.tkgroupbd.pusti.api.controllers.orders.secondary;
// package com.tkgroupbd.pusti.api.controllers.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.CollectionProduct;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.CollectionProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.CollectionProductServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @Tag(name = "Collection Product")
// @RestController
// @RequestMapping("/collectionProduct")
// public class CollectionProductController {
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private CollectionProductServiceImpl collectionProductService;

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> addSalesOrderRemark(@RequestBody
// CollectionProductRequest request) {
// MessageResponse salesOrderRemark =
// collectionProductService.createCollectionProduct(request);
// return new ResponseEntity<>(salesOrderRemark, HttpStatus.CREATED);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<CollectionProduct>>
// updateCollectionProduct(@PathVariable String id,
// @RequestBody CollectionProductRequest request) {
// Optional<CollectionProduct> collectionProduct =
// collectionProductService.updateCollectionProduct(id, request);
// return new ResponseEntity<Optional<CollectionProduct>>(collectionProduct,
// HttpStatus.OK);
// }

// @PutMapping("/update/status/{id}")
// public ResponseEntity<Optional<CollectionProduct>>
// updateStatusCollectionProduct(@PathVariable String id,
// @RequestBody CollectionProductRequest request) {
// Optional<CollectionProduct> collectionProduct =
// collectionProductService.updateStatusCollectionProduct(id,
// request);
// return new ResponseEntity<Optional<CollectionProduct>>(collectionProduct,
// HttpStatus.OK);
// }

// }
