package com.tkgroupbd.pusti.api.services.orders.secondary;
// package com.tkgroupbd.pusti.api.services.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.PendingProduct;
// import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.PendingProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.secondaryorders.DeliveredProductRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.secondaryorders.PendingProductRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class PendingProductServiceImpl implements PendingProductService {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private PendingProductRepository pendingProductRepository;
// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private DeliveredProductRepository deliveredProductRepository;

// @Override
// public MessageResponse createPendingProduct(PendingProductRequest request) {
// try {
// PendingProduct pendingProduct = new PendingProduct();

// pendingProduct.setSku(request.getSku());
// pendingProduct.setQuantity(request.getQuantity());
// pendingProduct.setDeliveryStatus(request.getDeliveryStatus());
// pendingProduct.setReceivedFrom(request.getReceivedFrom());
// pendingProduct.setProductItem(request.getProductItem());
// pendingProduct.setOutletAmendment(request.getOutletAmendment());
// pendingProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// pendingProduct.setCreatedAt(request.getCreatedAt());
// pendingProduct.setPrimaryOrderApprovals(request.getPrimaryOrderApprovals());
// pendingProduct.setDeliveryType(request.getDeliveryType());

// pendingProductRepository.save(pendingProduct);
// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }
// }

// @Override
// public List<PendingProduct> getPendingProductsByDate() {
// LocalDate createdAt = LocalDate.now();
// return pendingProductRepository.findByCreatedAt(createdAt);
// }

// @Override
// public List<PendingProduct> getPendingProductsByDateBetween(LocalDate
// startDate, LocalDate endDate) {
// return pendingProductRepository.findByCreatedAtBetween(startDate, endDate);
// }

// @Override
// public List<PendingProduct> findPendingProductByCreatedAtAndSku(LocalDate
// startDate, String sku) {
// return
// pendingProductRepository.findPendingProductByCreatedAtAndSku(startDate, sku);
// }

// @Override
// public List<PendingProduct> getByCreatedAtBetweenLastManualDays(LocalDate
// endDate, int i) {
// LocalDate startDate = endDate.minusDays(i);
// return pendingProductRepository.findByCreatedAtBetween(startDate, endDate);
// }

// /**
// * firstly find all pending product that status is false
// * secondly add data in delevery product table
// *
// * @return
// */
// @Override
// public MessageResponse addAllInDeliveredFormPending() {

// try {
// List<PendingProduct> pendingProductList =
// pendingProductRepository.findByStatus(true);

// List<DeliveredProduct> deliveredProducts = new ArrayList<>();
// for (PendingProduct pendingProduct : pendingProductList) {
// DeliveredProduct deliveredProduct = new DeliveredProduct();
// deliveredProduct.setPendingProduct(pendingProduct);
// deliveredProduct.setProductItem(pendingProduct.getProductItem());
// deliveredProduct.setReceivedFrom(pendingProduct.getReceivedFrom());
// deliveredProduct.setDeliveryStatus(pendingProduct.getDeliveryStatus());
// deliveredProduct.setOutletAmendment(pendingProduct.getOutletAmendment());
// deliveredProduct.setAssignedSalesOfficer(pendingProduct.getAssignedSalesOfficer());
// deliveredProduct.setQuantity(pendingProduct.getQuantity());
// deliveredProduct.setPendingProduct(pendingProduct);

// deliveredProducts.add(deliveredProduct);
// }
// deliveredProductRepository.saveAll(deliveredProducts);

// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }
// }

// @Override
// public List<PendingProduct> getAllPendingProductsByOrderId(int id) {
// List<PendingProduct> pendingProductList = new ArrayList<>();
// try {
// return pendingProductList =
// pendingProductRepository.findByPrimaryOrderApprovalProcess(id);
// } catch (Exception e) {
// throw new ResourceNotFoundException("PendingProduct", "id", id);
// }
// }

// @Override
// public List<PendingProduct> getAllPendingProducts() {
// List<PendingProduct> pendingProductList = pendingProductRepository.findAll();
// return pendingProductList;
// }
// /**
// * update pending product quantity
// * add how many product is request to delivered
// * save delivered product in delivered table
// * if quantity is equal to pending product then it will update delivery status
// also
// * @param id
// * @param request
// * @return
// */
// @Override
// public MessageResponse
// updatePendingProductQuantityAndAddToDeleveryProduct(Integer id,
// PendingProductRequest request) {
// Optional<PendingProduct> results = pendingProductRepository.findById(id);
// if (results.isPresent()) {
// PendingProduct pendingProduct = results.get();

// DeliveredProduct deliveredProduct = new DeliveredProduct();

// deliveredProduct.setPendingProduct(pendingProduct);
// deliveredProduct.setProductItem(pendingProduct.getProductItem());
// deliveredProduct.setReceivedFrom(pendingProduct.getReceivedFrom());
// deliveredProduct.setDeliveryStatus(pendingProduct.getDeliveryStatus());
// deliveredProduct.setOutletAmendment(pendingProduct.getOutletAmendment());
// deliveredProduct.setAssignedSalesOfficer(pendingProduct.getAssignedSalesOfficer());

// deliveredProduct.setPendingProduct(pendingProduct);

// if (pendingProduct.getQuantity()==request.getQuantity()) {
// deliveredProduct.setQuantity(request.getQuantity());
// deliveredProductRepository.save(deliveredProduct);
// pendingProduct.setDeliveryStatus(DeliveryStatus.valueOf("COMPLETED"));
// pendingProduct.setQuantity(pendingProduct.getQuantity() -
// request.getQuantity());
// pendingProductRepository.save(pendingProduct);
// return new MessageResponse(Message.SUCCESS_ORDER);

// }else if (pendingProduct.getQuantity()>request.getQuantity()){
// deliveredProduct.setQuantity(request.getQuantity());
// deliveredProductRepository.save(deliveredProduct);
// pendingProduct.setDeliveryStatus(DeliveryStatus.valueOf("IN_PROGRESS"));
// pendingProduct.setQuantity(pendingProduct.getQuantity() -
// request.getQuantity());
// pendingProductRepository.save(pendingProduct);
// return new MessageResponse(Message.SUCCESS_ORDER);
// }else {
// return new MessageResponse(Message.FAILED_ORDER);
// }

// } else {
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }

// @Override
// public Optional<PendingProduct> updatePendingProduct(Integer id,
// PendingProductRequest request) {
// Optional<PendingProduct> results = pendingProductRepository.findById(id);
// if (results.isPresent()) {
// PendingProduct pendingProduct = results.get();

// pendingProduct.setSku(request.getSku());
// pendingProduct.setDeliveryStatus(request.getDeliveryStatus());
// pendingProduct.setReceivedFrom(request.getReceivedFrom());
// pendingProduct.setProductItem(request.getProductItem());
// pendingProduct.setOutletAmendment(request.getOutletAmendment());
// pendingProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// pendingProduct.setCreatedAt(request.getCreatedAt());
// pendingProduct.setPrimaryOrderApprovals(request.getPrimaryOrderApprovals());
// pendingProduct.setQuantity(request.getQuantity());

// pendingProductRepository.save(pendingProduct);

// } else {
// throw new ResourceNotFoundException("Pending Product", "id", id);
// }
// return results;
// }

// @Override
// public Optional<PendingProduct> updatePendingProductStatus(Integer id,
// PendingProductRequest request) {
// Optional<PendingProduct> results = pendingProductRepository.findById(id);
// if (results.isPresent()) {
// PendingProduct pendingProduct = results.get();

// pendingProduct.setStatus(request.isStatus());

// pendingProductRepository.save(pendingProduct);
// } else {
// throw new ResourceNotFoundException("Pending Product", "id", id);
// }
// return results;
// }
// }
