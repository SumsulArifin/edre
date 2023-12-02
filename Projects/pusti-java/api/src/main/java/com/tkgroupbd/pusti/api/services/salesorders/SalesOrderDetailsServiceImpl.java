// package com.tkgroupbd.pusti.api.services.salesorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;
// import
// com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderDetailsRepository;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class SalesOrderDetailsServiceImpl implements SalesOrderDetailsService
// {
// @SuppressWarnings(Message.AUTOWIRED_OK)
// @Autowired
// private SalesOrderDetailsRepository salesOrderDetailsRepository;

// @Override
// public List<SalesOrderDetails> salesOrderDetails() {
// List<SalesOrderDetails> salesOrderDetails =
// salesOrderDetailsRepository.findByCreatedAt(LocalDate.now());
// return salesOrderDetails;
// }

// @Override
// public List<SalesOrderDetails> salesOrderDetailsBetween(LocalDate startDate,
// LocalDate endDate) {
// List<SalesOrderDetails> salesOrderDetails =
// salesOrderDetailsRepository.findByCreatedAtBetween(startDate,
// endDate);
// return salesOrderDetails;
// }

// @Override
// public List<SalesOrderDetails>
// salesOrderDetailsAllByAssignedSalesOfficerId(int id) {
// List<SalesOrderDetails> salesOrderDetails =
// salesOrderDetailsRepository.findByAssignedSalesOfficer(id);
// return salesOrderDetails;
// }

// @Override
// public List<Object[]> getAssignedSalesOfficerOrderedMost() {
// return salesOrderDetailsRepository.findAssignedSalesOfficerOrderedMost();
// }

// @Override
// public List<Object[]> getAssignedSalesOfficerOrderedLeast() {
// return salesOrderDetailsRepository.findAssignedSalesOfficerOrderedLeast();
// }

// @Override
// public List<Object[]> findSKUByRoute_ID(int routeId) {
// return salesOrderDetailsRepository.findSKUByRouteId(routeId);
// }

// @Override
// public List<Object[]> findSKUByDistributor_id(int distributor_id) {
// return salesOrderDetailsRepository.findSKUByDistributorId(distributor_id);
// }

// @Override
// public List<Object[]> findCategorySalesReportByRouteId(int routeId) {
// return salesOrderDetailsRepository.findCategorySalesReportByRouteId(routeId);
// }

// @Override
// public List<SalesOrderDetails> salesOrderDetailsBySku(String sku, int year,
// int month) {
// LocalDate startDate = LocalDate.of(year, month, 1);
// LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
// return
// salesOrderDetailsRepository.findSalesOrderDetailsBySkuAndCreatedAtBetween(sku,
// startDate, endDate);
// }

// @Override
// public List<SalesOrderDetails> salesOrderDetailsBySku(String sku) {
// LocalDate date = LocalDate.now();
// String sku1 = sku.trim().toLowerCase();
// return
// salesOrderDetailsRepository.findSalesOrderDetailsBySkuAndCreatedAt(sku1,
// date);
// }

// public List<SalesOrderDetails> salesOrderDetailsByRouteLastManualDays(int
// routeId) {
// LocalDate endDate = LocalDate.now();
// LocalDate startDate = endDate.minusDays(7);
// return
// salesOrderDetailsRepository.findSalesOrderDetailsBySalesOrder_Route_RouteIdAndCreatedAtBetween(routeId,
// startDate, endDate);
// }

// @Override
// public List<SalesOrderDetails> getSalesOrderDetailsByCategoryIdAndDate(int
// categoryId, LocalDate startDate,
// LocalDate endDate) {
// return
// salesOrderDetailsRepository.findSalesOrderDetailsByCategory_IdAndCreatedAtBetween(categoryId,
// startDate,
// endDate);
// }

// }
