package com.tkgroupbd.pusti.api.controllers.factory;
// package com.tkgroupbd.pusti.api.controllers.inventory;

// import
// com.tkgroupbd.pusti.api.data.payloads.requests.distributions.ProductDistributionDeliveryRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.mastersettings.products.ProductsRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.primaryorders.PrimaryOrderApprovalsRepository;
// import com.tkgroupbd.pusti.api.services.inventory.SKUUpdatedServiceImpl;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @Tag(name = "SKU Manager")
// @RequestMapping("/sku")
// public class SKUUpdatedController {
// @Autowired
// SKUUpdatedServiceImpl skuUpdatedService;

// @Autowired
// PrimaryOrderApprovalsRepository primaryOrderApprovalsRepository;
// @Autowired
// ProductsRepository productsRepository;

// @PostMapping("/update/{SKU}")
// public ResponseEntity<MessageResponse> createDistributionDelivery(
// @RequestBody ProductDistributionDeliveryRequest request, @PathVariable String
// SKU) {
// MessageResponse distributionDelivery =
// skuUpdatedService.createDeliveryAndUpdateSKU(request, request.getSku(),
// request.getQuantity());
// return new ResponseEntity<>(distributionDelivery, HttpStatus.CREATED);
// }

// }
