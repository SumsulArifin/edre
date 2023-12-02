// package com.tkgroupbd.pusti.api.services.salesorders;

// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;
// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderDetailsRequest;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderDetailsRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderRepository;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class SalesOrderServiceImpl implements SalesOrderService {
// @Autowired
// SalesOrderDetailsRepository orderDetailsRepository;
// @Autowired
// SalesOrderRepository salesOrderRepository;

// @Override
// public MessageResponse addSalesOrder(SalesOrderRequest salesOrderRequest) {

// SalesOrder salesOrder = new SalesOrder();
// salesOrder.setAssignedSalesOfficer(salesOrderRequest.getAssignedSalesOfficer());
// salesOrder.setSalesOrganizationId(salesOrderRequest.getSalesOrganizationId());
// salesOrder.setRoute(salesOrderRequest.getRoute());
// salesOrder.setOutlet(salesOrderRequest.getOutlet());
// salesOrder.setDistributor(salesOrderRequest.getDistributor());
// salesOrder.setDeliveryStatus(salesOrderRequest.getDeliveryStatus());
// salesOrder.setOrderStatus(salesOrderRequest.isOrderStatus());
// salesOrder.setRegion(salesOrderRequest.getRegion());
// salesOrder.setZone(salesOrderRequest.getZone());
// salesOrder.setCreatedAt(salesOrderRequest.getCreatedAt());
// salesOrder.setCreatedBy(salesOrderRequest.getCreatedBy());
// salesOrder.setUpdatedAt(salesOrderRequest.getUpdatedAt());
// salesOrder.setUpdatedBy(salesOrderRequest.getUpdatedBy());
// salesOrder.setDeletedAt(salesOrderRequest.getDeletedAt());
// salesOrder.setDeletedBy(salesOrderRequest.getDeletedBy());
// salesOrder = salesOrderRepository.save(salesOrder);

// for (SalesOrderDetailsRequest salesOrderDetailsRequest :
// salesOrderRequest.getSalesOrderDetailsRequests()) {
// SalesOrderDetails salesOrderDetails = new SalesOrderDetails();

// salesOrderDetails.setSalesOrder(salesOrder);

// salesOrderDetails.setSku(salesOrderDetailsRequest.getSku());
// salesOrderDetails.setDistribution_price(salesOrderDetailsRequest.getDistribution_price());
// salesOrderDetails.setTrade_price(salesOrderDetailsRequest.getTrade_price());
// salesOrderDetails.setTrade_price_discount(salesOrderDetailsRequest.getTrade_price_discount());
// salesOrderDetails.setOrdered_quantity(salesOrderDetailsRequest.getOrdered_quantity());
// salesOrderDetails.setOrdered_quantity_free(salesOrderDetailsRequest.getOrdered_quantity_free());
// salesOrderDetails
// .setOrdered_quantity_free_bundle(salesOrderDetailsRequest.getOrdered_quantity_free_bundle());
// salesOrderDetails.setOutlet_discount(salesOrderDetailsRequest.getOutlet_discount());
// salesOrderDetails.setReceipt_amount_discount(salesOrderDetailsRequest.getReceipt_amount_discount());
// salesOrderDetails.setRemarks(salesOrderDetailsRequest.getRemarks());

// salesOrderDetails.setCategory(salesOrderDetailsRequest.getCategory());
// salesOrderDetails.setProduct(salesOrderDetailsRequest.getProduct());
// salesOrderDetails.setProductItem(salesOrderDetailsRequest.getProductItem());

// salesOrderDetails.setPayment_mode(salesOrderDetailsRequest.getPayment_mode());
// salesOrderDetails.setCreatedAt(salesOrderDetailsRequest.getCreatedAt());
// salesOrderDetails.setCreatedBy(salesOrderDetailsRequest.getCreatedBy());
// salesOrderDetails.setUpdatedAt(salesOrderDetailsRequest.getUpdatedAt());
// salesOrderDetails.setUpdatedBy(salesOrderDetailsRequest.getUpdatedBy());
// salesOrderDetails.setDeletedAt(salesOrderDetailsRequest.getDeletedAt());
// salesOrderDetails.setDeletedBy(salesOrderDetailsRequest.getDeletedBy());

// // Save the productDetail entity
// orderDetailsRepository.save(salesOrderDetails);

// }

// return new MessageResponse(Message.SUCCESS_CREATION);

// }

// @Override
// public List<Object[]> getAllSalesOrder() {
// return null;
// }

// @Override
// public MessageResponse updateSalesOrder(int id, SalesOrderRequest
// salesOrderRequest) {

// try {
// Optional<SalesOrder> result = salesOrderRepository.findById(id);
// if (!result.isPresent()) {
// return new MessageResponse(Message.FAILED_UPDATE);
// }
// SalesOrder salesOrder = result.get();

// salesOrder.setAssignedSalesOfficer(salesOrderRequest.getAssignedSalesOfficer());
// salesOrder.setSalesOrganizationId(salesOrderRequest.getSalesOrganizationId());
// salesOrder.setRoute(salesOrderRequest.getRoute());
// salesOrder.setOutlet(salesOrderRequest.getOutlet());
// salesOrder.setDistributor(salesOrderRequest.getDistributor());
// salesOrder.setDeliveryStatus(salesOrderRequest.getDeliveryStatus());
// salesOrder.setOrderStatus(salesOrderRequest.isOrderStatus());
// salesOrder = salesOrderRepository.save(salesOrder);
// for (SalesOrderDetailsRequest salesOrderDetailsRequest :
// salesOrderRequest.getSalesOrderDetailsRequests()) {
// Optional<SalesOrderDetails> orderDetails = orderDetailsRepository
// .findById(salesOrderDetailsRequest.getId());
// if (orderDetails.isPresent()) {
// SalesOrderDetails salesOrderDetails = orderDetails.get();
// salesOrderDetails.setSku(salesOrderDetailsRequest.getSku());
// salesOrderDetails.setDistribution_price(salesOrderDetailsRequest.getDistribution_price());
// salesOrderDetails.setTrade_price(salesOrderDetailsRequest.getTrade_price());
// salesOrderDetails.setTrade_price_discount(salesOrderDetailsRequest.getTrade_price_discount());
// salesOrderDetails.setOrdered_quantity(salesOrderDetailsRequest.getOrdered_quantity());
// salesOrderDetails.setOrdered_quantity_free(salesOrderDetailsRequest.getOrdered_quantity_free());
// salesOrderDetails.setOrdered_quantity_free(salesOrderDetailsRequest.getOrdered_quantity_free());
// salesOrderDetails.setOrdered_quantity_free_bundle(
// salesOrderDetailsRequest.getOrdered_quantity_free_bundle());
// salesOrderDetails.setOutlet_discount(salesOrderDetailsRequest.getOutlet_discount());
// salesOrderDetails.setReceipt_amount_discount(salesOrderDetailsRequest.getReceipt_amount_discount());
// salesOrderDetails.setRemarks(salesOrderDetailsRequest.getRemarks());
// salesOrderDetails.setCategory(salesOrderDetailsRequest.getCategory());
// salesOrderDetails.setProduct(salesOrderDetailsRequest.getProduct());
// salesOrderDetails.setPayment_mode(salesOrderDetailsRequest.getPayment_mode());
// orderDetailsRepository.save(salesOrderDetails);
// }
// }
// return new MessageResponse(Message.SUCCESS_UPDATE);

// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_UPDATE);
// }
// }

// @Override
// public List<SalesOrder> findAllSalesOrderByRouteId(int id) {
// return salesOrderRepository.findSalesOrderByRouteId(id);
// }

// @Override
// public List<SalesOrder> findAllSalesOrderByOutletId(int id) {
// return salesOrderRepository.findSalesOrderByOutletId(id);
// }

// @Override
// public List<SalesOrder> findAllSalesOrderBySalesOfficerId(int id) {
// return salesOrderRepository.findSalesOrderBySalesOfficerId(id);
// }

// @Override
// public List<SalesOrder> findAllSalesOrderByDistributorId(int id) {
// return salesOrderRepository.findSalesOrderByDistributorId(id);
// }

// @Override
// public List<SalesOrder> findAllSalesOrderByRegionId(int id) {
// return salesOrderRepository.findSalesOrderByRegionId(id);
// }

// @Override
// public List<SalesOrder> findAllSalesOrderByZoneId(int id) {
// return salesOrderRepository.findSalesOrderByZoneId(id);
// }

// @Override
// public List<Object> findAllSalesOrderBySelectedDate(int id) {

// return null;
// }

// @Override
// public List<SalesOrder> sOWiseSalesReportByTheSelectedDate(int assignedId,
// LocalDate startDate, LocalDate endDate) {
// return
// salesOrderRepository.findSOWiseSalesReportByTheSelectedDate(assignedId,
// startDate, endDate);
// }

// public List<SalesOrder> findOrderStatusByOutletId(int id, boolean
// orderStatus, LocalDate startDate,
// LocalDate endDate) {
// return salesOrderRepository.findOderStatusByOutletId(id, orderStatus,
// startDate, endDate);
// }

// public Integer findMaxOutletId() {
// LocalDate date = LocalDate.now();
// int salesOrders = salesOrderRepository.findMaxOutletIdByOrderOutletId(date);
// return salesOrders;
// }

// public Integer findMinOutletId() {
// LocalDate date = LocalDate.now();
// int salesOrders = salesOrderRepository.findMinOutletIdByOrderOutletId(date);
// return salesOrders;
// }

// public List<Object[]> orderMostOutlet(LocalDate startDate, LocalDate endDate)
// {
// List<Object[]> mostOutlets =
// salesOrderRepository.findOrderedMostAndMinOutlet(startDate, endDate);
// return mostOutlets;
// }

// public List<SalesOrder> findByCreatedAtBetweenAndAssignedSalesOfficer(int
// year, int month, int soId) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return
// salesOrderRepository.findByCreatedAtBetweenAndAssignedSalesOfficer(startDate,
// endDate, soId);
// }

// public List<SalesOrder> getBySkyWithDateAndSoWise(LocalDate date, int id) {
// return salesOrderRepository.findBySkyWithDateAndSoWise(date, id);
// }

// public List<SalesOrder> findByMonthlyBetween(int year, int month) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return salesOrderRepository.findByCreatedAtBetween(startDate, endDate);
// }

// /**
// * give route id
// *
// * @param id
// * @return : a category list
// */
// public List<Object[]> getByCreatedAtAndRouteWise(int year, int month, int id)
// {
// LocalDate date = LocalDate.of(year, month, 1);
// return salesOrderRepository.findByCreatedAtAndRouteWise(date, id);
// }

// public List<Object[]> achievementDateByCategory(int id) {
// return salesOrderRepository.achievementDateByCategory(id);
// }

// public List<Object[]> salesAchievementByCategory(int id) {
// return salesOrderRepository.salesAchievementByCategory(id);
// }

// public List<Object[]> findRouteAndOutletByCreatedAt(LocalDate date) {
// return salesOrderRepository.findRouteAndOutletByCreatedAt(date);
// }

// }
