package com.tkgroupbd.pusti.api.services.orders.secondary;
// package com.tkgroupbd.pusti.api.services.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.secondaryorders.DeliveredProductRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderDetailsRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.secondaryorders.DeliveredProductRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.text.DecimalFormat;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class DeliveredProductServiceImpl implements DeliveredProductService {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private DeliveredProductRepository repository;

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SalesOrderDetailsRepository salesOrderDetailsRepository;

// @Override
// public MessageResponse createDeliveredProduct(DeliveredProductRequest
// request) {
// try {
// DeliveredProduct deliveredProduct = new DeliveredProduct();

// deliveredProduct.setScheduleOutlet(request.getScheduleOutlet());
// deliveredProduct.setVisitedOutlet(request.getVisitedOutlet());
// deliveredProduct.setQuantity(request.getQuantity());
// deliveredProduct.setReceivedFrom(request.getReceivedFrom());
// deliveredProduct.setDeliveryStatus(request.getDeliveryStatus());
// deliveredProduct.setDeliveryType(request.getDeliveryType());
// deliveredProduct.setProductItem(request.getProductItem());
// deliveredProduct.setOutletAmendment(request.getOutletAmendment());
// deliveredProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// deliveredProduct.setPendingProduct(request.getPendingProduct());
// deliveredProduct.setDistributor(request.getDistributor());
// deliveredProduct.setCreatedAt(request.getCreatedAt());
// deliveredProduct.setUpdatedAt(request.getUpdatedAt());
// deliveredProduct.setDeletedAt(request.getDeletedAt());

// repository.save(deliveredProduct);
// return new MessageResponse(Message.SUCCESS_CREATION);
// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }
// }

// @Override
// public Optional<DeliveredProduct> updateDeliveredProduct(Integer id,
// DeliveredProductRequest request) {

// Optional<DeliveredProduct> result = Optional.ofNullable(
// repository.findById(id).orElseThrow(() -> new
// ResourceNotFoundException("DeliveredProduct", "id", id)));

// if (result.isPresent()) {
// DeliveredProduct deliveredProduct = result.get();

// deliveredProduct.setScheduleOutlet(request.getScheduleOutlet());
// deliveredProduct.setVisitedOutlet(request.getVisitedOutlet());
// deliveredProduct.setQuantity(request.getQuantity());
// deliveredProduct.setReceivedFrom(request.getReceivedFrom());
// deliveredProduct.setDeliveryStatus(request.getDeliveryStatus());
// deliveredProduct.setDeliveryType(request.getDeliveryType());
// deliveredProduct.setProductItem(request.getProductItem());
// deliveredProduct.setOutletAmendment(request.getOutletAmendment());
// deliveredProduct.setAssignedSalesOfficer(request.getAssignedSalesOfficer());
// deliveredProduct.setPendingProduct(request.getPendingProduct());
// deliveredProduct.setDistributor(request.getDistributor());
// deliveredProduct.setCreatedAt(request.getCreatedAt());
// deliveredProduct.setUpdatedAt(request.getUpdatedAt());
// deliveredProduct.setDeletedAt(request.getDeletedAt());

// repository.save(deliveredProduct);
// } else {
// throw new ResourceNotFoundException("DeliveredProduct", "id", id);
// }
// return result;
// }

// @Override
// public DeliveredProduct getDeliveredProductById(Integer id) {
// return repository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("DeliveredProduct", "id",
// id));
// }

// @Override
// public List<DeliveredProduct> getAllDeliveredProduct() {
// return repository.findAll();
// }

// @Override
// public List<DeliveredProduct> getAllByDateRange(LocalDate startDate,
// LocalDate endDate) {
// return repository.findByCreatedAtBetween(startDate, endDate);
// }

// @Override
// public List<DeliveredProduct> findByCreatedAt(LocalDate createdAt) {
// ;
// List<DeliveredProduct> products = repository.findByCreatedAt(createdAt);
// return products;
// }

// public List<DeliveredProduct> findDeliveredProductByMonthly(int year, int
// month) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return repository.findByCreatedAtBetween(startDate, endDate);
// }

// @Override
// public List<Object[]> getDeliveredProductByDistributorId(int distributor_id)
// {
// return repository.findDeliveredProductByDistributor(distributor_id);
// }

// @Override
// public List<Object[]> getdWeeklyDeliveredReport(DeliveryStatus
// deliveryStatus, LocalDate startDate,
// LocalDate endDate) {
// return repository.findWeeklyDeliveredReport(deliveryStatus, startDate,
// endDate);
// }

// public List<DeliveredProduct>
// findDeliveredProductByMonthlyByDistributorId(int year, int month, int id) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// List<DeliveredProduct> findDeliveredAllByDistributorId = repository
// .findMonthlyDeliveredReportByDistributor(startDate, endDate, id);
// return findDeliveredAllByDistributorId;
// }

// public List<DeliveredProduct> findDeliveredProductByCreatedAtAndSku(LocalDate
// createdAt, String sku) {
// return repository.findDeliveredProductByCreatedAtAndSku(createdAt, sku);
// }

// /**
// * pending product has gone to db as delivered product and sales order
// delivered
// * product is secondary sales product
// *
// * @return a ratio of salesDetailsCategory/deliveredProductCategory * 100%
// */
// public String findAllRatioCategory() {
// double deliveredCategoryTotal = repository.findTotalDeliveredCategory();
// double saleOrderDetailsCategoryTotal =
// salesOrderDetailsRepository.findAllSaleOrderDetailsCategory();
// System.out.println(saleOrderDetailsCategoryTotal);
// double percentage = saleOrderDetailsCategoryTotal / deliveredCategoryTotal;

// System.out.println(percentage + "---------");
// // Format the percentage as a string with a percentage sign
// DecimalFormat decimalFormat = new DecimalFormat("0.00%");
// String formattedPercentage = decimalFormat.format(percentage);
// return formattedPercentage;
// }

// @Override
// public List<DeliveredProduct> getDeliveryStatusByCreatedAtBetween(LocalDate
// startDate, LocalDate endDate) {
// return repository.findDeliveryStatusByCreatedAtBetween(startDate, endDate);
// }

// // @Override
// // public List<Object[]> getDeliveredProductByDistributorId(int
// distributor_id)
// // {
// // return repository.findDeliveredProductByDistributor(distributor_id);
// // }

// @Override
// public List<DeliveredProduct> findByCreatedAts(LocalDate createdAt) {

// throw new UnsupportedOperationException("Unimplemented method
// 'findByCreatedAts'");
// }

// @Override
// public List<DeliveredProduct> findByCreated(LocalDate createdAt) {

// throw new UnsupportedOperationException("Unimplemented method
// 'findByCreated'");
// }

// // @Override
// // public List<Object[]> getDeliveredProductByDistributorId(int
// distributor_id)
// // {
// // // TODO Auto-generated method stub
// // throw new UnsupportedOperationException("Unimplemented method
// // 'getDeliveredProductByDistributorId'");
// // }

// // @Override
// // public List<Object[]> getdWeeklyDeliveredReport(DeliveryStatus
// // deliveryStatus, LocalDate startDate,
// // LocalDate endDate) {
// // // TODO Auto-generated method stub
// // throw new UnsupportedOperationException("Unimplemented method
// // 'getdWeeklyDeliveredReport'");
// // }

// public List<Object[]> findDeliveredProductByCategory(int id) {
// return repository.findDeliveredProductByCategory(id);
// }

// public List<DeliveredProduct> getDbDailyReceivedProduct(int id) {
// LocalDate date = LocalDate.now();
// return repository.findDbDailyReceivedProduct(date, id);
// }

// }
