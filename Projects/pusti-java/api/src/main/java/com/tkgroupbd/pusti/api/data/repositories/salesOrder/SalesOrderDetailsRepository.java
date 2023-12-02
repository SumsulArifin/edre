// package com.tkgroupbd.pusti.api.data.repositories.salesOrder;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;

// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface SalesOrderDetailsRepository extends
// JpaRepository<SalesOrderDetails, Integer> {
// List<SalesOrderDetails> findByCreatedAt(LocalDate date);

// List<SalesOrderDetails> findByCreatedAtBetween(LocalDate startDate, LocalDate
// endDate);

// @Query("select s from SalesOrderDetails s where
// s.salesOrder.assignedSalesOfficer.assignedId=?1 ")
// List<SalesOrderDetails> findByAssignedSalesOfficer(int id);

// /**
// * maximum sales officer which quantity is most
// *
// * @return
// */
// @Query("SELECT sod.salesOrder.assignedSalesOfficer, SUM(sod.ordered_quantity)
// AS totalOrderedQuantity " +
// "FROM SalesOrderDetails sod " +
// "GROUP BY sod.salesOrder.assignedSalesOfficer " +
// "ORDER BY totalOrderedQuantity DESC")
// List<Object[]> findAssignedSalesOfficerOrderedMost();

// @Query("SELECT sod.salesOrder.assignedSalesOfficer, SUM(sod.ordered_quantity)
// AS totalOrderedQuantity " +
// "FROM SalesOrderDetails sod " +
// "GROUP BY sod.salesOrder.assignedSalesOfficer " +
// "ORDER BY totalOrderedQuantity ASC")
// List<Object[]> findAssignedSalesOfficerOrderedLeast();

// @Query("select count (distinct s.productItem.product.category.id) from
// SalesOrderDetails s")
// int findAllSaleOrderDetailsCategory();

// @Query("select sod.sku from SalesOrderDetails sod where
// sod.salesOrder.route.routeId=?1")
// List<Object[]> findSKUByRouteId(int id);

// @Query("select sod.category from SalesOrderDetails sod where
// sod.salesOrder.route.routeId=?1")
// List<Object[]> findCategorySalesReportByRouteId(int routeId);

// @Query("select sod.sku,sod.createdBy from SalesOrderDetails sod where
// sod.salesOrder.distributor.distributor_id=?1")
// List<Object[]> findSKUByDistributorId(int distributor_id);

// List<SalesOrderDetails> findSalesOrderDetailsBySkuAndCreatedAtBetween(String
// sku, LocalDate startDate,
// LocalDate endDate);

// List<SalesOrderDetails> findSalesOrderDetailsBySkuAndCreatedAt(String sku,
// LocalDate date);

// List<SalesOrderDetails>
// findSalesOrderDetailsBySalesOrder_Route_RouteIdAndCreatedAtBetween(int id,
// LocalDate startDate, LocalDate endDate);

// public List<SalesOrderDetails>
// findSalesOrderDetailsByCategory_IdAndCreatedAtBetween(int id,
// LocalDate startDate, LocalDate endDate);

// }
