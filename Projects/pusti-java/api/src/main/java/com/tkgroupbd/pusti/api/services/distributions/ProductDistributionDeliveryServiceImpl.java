// package com.tkgroupbd.pusti.api.services.distributions;

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
// public class ProductDistributionDeliveryServiceImpl implements
// ProductDistributionDeliveryService {

// @Autowired
// private ProductDistributionDeliveryRepository distributionDeliveryRepository;

// @Autowired
// private ProductStockInRepository productStockInRepository;

// @Override
// public MessageResponse
// createDistributionDelivery(ProductDistributionDeliveryRequest request) {
// try {
// ProductDistributionDelivery distributionDelivery = new
// ProductDistributionDelivery();

// distributionDelivery.setSku(request.getSku());
// distributionDelivery.setQuantity(request.getQuantity());
// distributionDelivery.setProductSourceType(request.getProductSourceType());
// distributionDelivery.setProductSourceId(request.getProductSourceId());
// distributionDelivery.setRecipientType(request.getRecipientType());
// distributionDelivery.setDeliveryStatus(request.getDeliveryStatus());
// distributionDelivery.setPurchaseOrders(request.getPurchaseOrders());
// distributionDelivery.setProductDistributionSchedules(request.getProductDistributionSchedules());
// distributionDelivery.setCreatedBy(request.getCreatedBy());
// distributionDelivery.setCreatedAt(request.getCreatedAt());
// distributionDelivery.setUpdatedBy(request.getUpdatedBy());
// distributionDelivery.setDeletedAt(request.getDeletedAt());
// distributionDelivery.setPurchaseOrders(request.getPurchaseOrders());
// distributionDelivery.setProductDistributionSchedules(request.getProductDistributionSchedules());
// distributionDeliveryRepository.save(distributionDelivery);

// return new MessageResponse(Message.SUCCESS_CREATION);

// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }

// @Override
// public Optional<ProductDistributionDelivery>
// updateProductDistributionDelivery(int id, ProductDistributionDeliveryRequest
// request) {
// Optional<ProductDistributionDelivery> result =
// distributionDeliveryRepository.findById(id);

// if (result.isPresent()) {
// ProductDistributionDelivery distributionDelivery = result.get();

// distributionDelivery.setSku(request.getSku());
// distributionDelivery.setQuantity(request.getQuantity());
// distributionDelivery.setProductSourceType(request.getProductSourceType());
// distributionDelivery.setProductSourceId(request.getProductSourceId());
// distributionDelivery.setRecipientType(request.getRecipientType());
// distributionDelivery.setDeliveryStatus(request.getDeliveryStatus());
// distributionDelivery.setPurchaseOrders(request.getPurchaseOrders());
// distributionDelivery.setProductDistributionSchedules(request.getProductDistributionSchedules());
// distributionDelivery.setCreatedBy(request.getCreatedBy());
// distributionDelivery.setCreatedAt(request.getCreatedAt());
// distributionDelivery.setUpdatedBy(request.getUpdatedBy());
// distributionDelivery.setDeletedAt(request.getDeletedAt());
// distributionDelivery.setPurchaseOrders(request.getPurchaseOrders());
// distributionDelivery.setProductDistributionSchedules(request.getProductDistributionSchedules());

// distributionDeliveryRepository.save(distributionDelivery);

// } else {
// throw new ResourceNotFoundException("ProductDistributionDelivery", "id", id);
// }

// return result;
// }

// @Override
// public Optional<ProductDistributionDelivery>
// statusChangeProductDistributionDelivery(int id,
// ProductDistributionDeliveryRequest request) {
// Optional<ProductDistributionDelivery> result =
// distributionDeliveryRepository.findById(id);
// if (result.isPresent()) {
// ProductDistributionDelivery distributionDelivery = result.get();
// distributionDelivery.setStatus(request.isStatus());
// distributionDeliveryRepository.save(distributionDelivery);

// } else {
// throw new ResourceNotFoundException("ProductDistributionDelivery", "id", id);
// }
// return result;
// }

// public void updateProductStockInBySkuName(String sku, int qty) {
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
// createDeleveryAndUpdateStockInDistributionDelivery(ProductDistributionDeliveryRequest
// request, String sku, int qty) {
// try {
// createDistributionDelivery(request);
// updateProductStockInBySkuName(sku, qty);
// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }
// }
// }
