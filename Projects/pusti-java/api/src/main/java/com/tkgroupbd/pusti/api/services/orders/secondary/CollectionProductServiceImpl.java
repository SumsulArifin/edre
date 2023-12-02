package com.tkgroupbd.pusti.api.services.orders.secondary;
// package com.tkgroupbd.pusti.api.services.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.CollectionProduct;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.CollectionProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.secondaryorders.CollectionProductRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class CollectionProductServiceImpl implements CollectionProductService
// {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private CollectionProductRepository collectionProductRepository;
// @Override
// public MessageResponse createCollectionProduct(CollectionProductRequest
// request) {
// try {
// CollectionProduct collectionProduct = new CollectionProduct();

// collectionProduct.setScheduleOutlet(request.getScheduleOutlet());
// collectionProduct.setVisitedOutlet(request.getVisitedOutlet());
// collectionProduct.setQuantity(request.getQuantity());
// collectionProduct.setReceivedFrom(request.getReceivedFrom());
// collectionProduct.setDeliveryStatus(request.getDeliveryStatus());
// collectionProduct.setDeliveryType(request.getDeliveryType());
// collectionProduct.setProductItem(request.getProductItem());
// collectionProduct.setOutletAmendment(request.getOutletAmendment());
// collectionProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// collectionProduct.setDeliveredProduct(request.getDeliveredProduct());
// collectionProduct.setCreatedAt(request.getCreatedAt());
// collectionProduct.setCreatedBy(request.getCreatedBy());
// collectionProduct.setUpdatedAt(request.getUpdatedAt());
// collectionProduct.setUpdatedBy(request.getUpdatedBy());
// collectionProduct.setDeletedAt(request.getDeletedAt());
// collectionProduct.setDeletedBy(request.getDeletedBy());
// collectionProduct.setStatus(request.isStatus());
// collectionProduct.setIp(request.getIp());
// collectionProduct.setBrowser(request.getBrowser());
// collectionProductRepository.save(collectionProduct);

// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }
// @Override
// public Optional<CollectionProduct> updateCollectionProduct(String sId,
// CollectionProductRequest request) {
// try {
// int id = Integer.parseInt(sId);
// Optional<CollectionProduct> result =
// Optional.ofNullable(collectionProductRepository.findById(id).orElseThrow(()
// -> new ResourceNotFoundException("CollectionProduct", "id", id)));
// if (result.isPresent()) {
// CollectionProduct collectionProduct = result.get();

// collectionProduct.setScheduleOutlet(request.getScheduleOutlet());
// collectionProduct.setVisitedOutlet(request.getVisitedOutlet());
// collectionProduct.setQuantity(request.getQuantity());
// collectionProduct.setReceivedFrom(request.getReceivedFrom());
// collectionProduct.setDeliveryStatus(request.getDeliveryStatus());
// collectionProduct.setDeliveryType(request.getDeliveryType());
// collectionProduct.setProductItem(request.getProductItem());
// collectionProduct.setOutletAmendment(request.getOutletAmendment());
// collectionProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// collectionProduct.setDeliveredProduct(request.getDeliveredProduct());
// collectionProduct.setCreatedAt(request.getCreatedAt());
// collectionProduct.setCreatedBy(request.getCreatedBy());
// collectionProduct.setUpdatedAt(request.getUpdatedAt());
// collectionProduct.setUpdatedBy(request.getUpdatedBy());
// collectionProduct.setDeletedAt(request.getDeletedAt());
// collectionProduct.setDeletedBy(request.getDeletedBy());
// collectionProduct.setStatus(request.isStatus());
// collectionProduct.setIp(request.getIp());
// collectionProduct.setBrowser(request.getBrowser());
// collectionProductRepository.save(collectionProduct);
// return result;
// } else {
// throw new ResourceNotFoundException("CollectionProduct", "id", id);
// }
// } catch (NumberFormatException e) {
// throw new IllegalArgumentException(sId + " is not a valid number format");
// } catch (ResourceNotFoundException e) {
// throw new RuntimeException(e);
// }
// }
// @Override
// public Optional<CollectionProduct> updateStatusCollectionProduct(String sId,
// CollectionProductRequest request) {
// try {
// int id = Integer.parseInt(sId);
// Optional<CollectionProduct> result =
// Optional.ofNullable(collectionProductRepository.findById(id).orElseThrow(()
// -> new ResourceNotFoundException("CollectionProduct", "id", id)));
// if (result.isPresent()) {
// CollectionProduct collectionProduct = result.get();
// collectionProduct.setStatus(request.isStatus());
// collectionProductRepository.save(collectionProduct);
// return result;
// } else {
// throw new ResourceNotFoundException("CollectionProduct", "id", id);
// }
// } catch (NumberFormatException e) {
// throw new IllegalArgumentException(sId + " is not a valid number format");
// } catch (ResourceNotFoundException e) {
// throw new RuntimeException(e);
// }
// }
// @Override
// public List<CollectionProduct> getAllCollectionProduct() {
// List<CollectionProduct> collectionProducts =
// collectionProductRepository.findAll();
// if (collectionProducts == null) {
// return null;
// } else {
// return collectionProducts;
// }
// }
// @Override
// public CollectionProduct getCollectionProductById(int id) {
// CollectionProduct collectionProduct = new CollectionProduct();
// if (collectionProductRepository.existsById(id)) {
// collectionProduct = collectionProductRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("CollectionProduct", "id",
// id));
// return collectionProduct;
// } else {
// System.out.println(" id - not matched.");
// return null;
// }
// }
// @Override
// public List<CollectionProduct> getCollectionProductBySoId(int id) {
// List<CollectionProduct> collectionProduct = new ArrayList<>();
// if (collectionProductRepository.existsById(id)) {
// collectionProduct = collectionProductRepository.findBySoId(id);
// } else {
// System.out.println(" id - not matched.");
// }
// return collectionProduct;
// }
// @Override
// public List<CollectionProduct> getCollectionProductByOutletAmendmentId(int
// id) {
// List<CollectionProduct> collectionProduct = new ArrayList<>();
// if (collectionProductRepository.existsById(id)) {
// collectionProduct = collectionProductRepository.findByOutletAmendmentId(id);
// } else {
// System.out.println("id not matched");
// }
// return collectionProduct;
// }

// @Override
// public List<CollectionProduct> getCollectionProductByDate() {
// LocalDate createdAt = LocalDate.now();
// return collectionProductRepository.findByCreatedAt(createdAt);
// }

// @Override
// public List<CollectionProduct> getCollectionProductByDateBetween(LocalDate
// startDate, LocalDate endDate) {
// return collectionProductRepository.findByCreatedAtBetween(startDate,
// endDate);

// }
// @Override
// public List<CollectionProduct> getCollectionProductByAnyDate(LocalDate date)
// {
// return collectionProductRepository.findByCreatedAt(date);
// }
// @Override
// public List<CollectionProduct> getByCreatedAtBetweenLastManualDays(LocalDate
// date, int howMany) {
// LocalDate startDate = date.minusDays(howMany);
// return collectionProductRepository.findByCreatedAtBetween(startDate, date);
// }
// @Override
// public Page<CollectionProduct> findCollectionProductByPagination(int offset,
// int pageSize) {
// Page<CollectionProduct> collectionProducts =
// collectionProductRepository.findAll(PageRequest.of(offset,pageSize));
// return collectionProducts;
// }
// @Override
// public Page<CollectionProduct> findSortedCollectionProductByPagination(int
// offset, int pageSize, String field) {
// Page<CollectionProduct> collectionProducts =
// collectionProductRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
// return collectionProducts;
// }
// @Override
// public List<CollectionProduct>
// findCollectionProductByCreatedAtAndSku(LocalDate createdAt, String sku) {
// return
// collectionProductRepository.findCollectionProductByCreatedAtAndSku(createdAt,
// sku);
// }
// @Override
// public List<CollectionProduct> findCollectionProductByMonthly(int year, int
// month) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return collectionProductRepository.findByCreatedAtBetween(startDate,
// endDate);
// }
// @Override
// public List<CollectionProduct>
// findByCreatedAtBetweenAndAssignedSalesOfficer(int year, int month, int soId)
// {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return
// collectionProductRepository.findByCreatedAtBetweenAndAssignedSalesOfficer(startDate,
// endDate, soId);
// }
// @Override
// public List<CollectionProduct> getCollectionProductsByZoneId(int id) {
// List<CollectionProduct> collectionProduct =
// collectionProductRepository.findByOutletAmendment_Route_Zone_ZoneId(id);
// return collectionProduct;
// }
// @Override
// public List<CollectionProduct> getCollectionProductsByRegionId(int id) {
// List<CollectionProduct> collectionProduct =
// collectionProductRepository.findByOutletAmendment_Route_Zone_Region_RegionId(id);
// return collectionProduct;
// }

// }
