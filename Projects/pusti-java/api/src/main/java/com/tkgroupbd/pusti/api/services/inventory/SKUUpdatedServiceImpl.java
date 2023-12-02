// package com.tkgroupbd.pusti.api.services.inventory;

// import
// com.tkgroupbd.pusti.api.data.models.entity.distributions.ProductDistributionDelivery;
// import com.tkgroupbd.pusti.api.data.models.entity.inventory.ProductStockIn;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.distributions.ProductDistributionDeliveryRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.distributions.ProductDistributionDeliveryRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.inventory.ProductStockInRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class SKUUpdatedServiceImpl {

// @Autowired
// private ProductStockInRepository productStockInRepository;
// @Autowired
// private ProductDistributionDeliveryRepository distributionDeliveryRepository;

// public MessageResponse
// createDistributionDelivery(ProductDistributionDeliveryRequest request) {
// try {
// ProductDistributionDelivery distributionDelivery = new
// ProductDistributionDelivery();
// distributionDelivery.setSku(request.getSku());
// distributionDelivery.setQuantity(request.getQuantity());
// distributionDeliveryRepository.save(distributionDelivery);
// return new MessageResponse(Message.SUCCESS_CREATION);

// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }
// public void updateSku(String sku, int qty) {
// Optional<ProductStockIn> result =
// productStockInRepository.findBySkuName(sku);
// if (result.isPresent()) {
// ProductStockIn productStockIn = result.get();
// productStockIn.setQuantity(productStockIn.getQuantity() - qty);
// productStockInRepository.save(productStockIn);
// } else {
// throw new ResourceNotFoundException("ProductStockIn", "id", qty);
// }
// }

// public MessageResponse
// createDeliveryAndUpdateSKU(ProductDistributionDeliveryRequest request, String
// sku, int qty) {
// try {
// createDistributionDelivery(request);
// updateSku(sku, qty);
// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }
// }
// }
