// package com.tkgroupbd.pusti.api.data.repositories.salesOrder;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;

// import java.sql.Date;
// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface SalesOrderRepository extends JpaRepository<SalesOrder,
// Integer> {

// @Query("select s from SalesOrder s where s.zone.zoneId=?1")
// public List<SalesOrder> findSalesOrderByZoneId(int id);

// @Query("select s from SalesOrder s where s.region.regionId=?1")
// public List<SalesOrder> findSalesOrderByRegionId(int id);

// @Query("select s from SalesOrder s where s.distributor.distributor_id=?1")
// public List<SalesOrder> findSalesOrderByDistributorId(int id);

// @Query("select s from SalesOrder s where
// s.assignedSalesOfficer.assignedId=?1")
// public List<SalesOrder> findSalesOrderBySalesOfficerId(int id);

// @Query("select s from SalesOrder s where s.outlet.outletId=?1")
// public List<SalesOrder> findSalesOrderByOutletId(int id);

// @Query("select s from SalesOrder s where s.route.routeId=?1")
// public List<SalesOrder> findSalesOrderByRouteId(int id);

// @Query("select s from SalesOrder s where s.createdAt=?1")
// public List<SalesOrder> findSalesOrderByDate(Date createdAt);

// @Query("select r from SalesOrder r where
// r.assignedSalesOfficer.assignedId=:assignedId and r.createdAt between
// :startDate and :endDate")
// List<SalesOrder> findSOWiseSalesReportByTheSelectedDate(@Param("assignedId")
// int assignedId,
// @Param("startDate") LocalDate startDate, @Param("startDate") LocalDate
// endDate);

// @Query("select s from SalesOrder s where s.outlet.outletId=:outletId and
// s.orderStatus=:orderStatus and s.createdAt between :startDate and :endDate")
// List<SalesOrder> findOderStatusByOutletId(@Param("outletId") int outletId,
// @Param("orderStatus") boolean orderStatus, @Param("startDate") LocalDate
// startDate,
// @Param("endDate") LocalDate endDate);

// @Query("select MAX(s.outlet.outletId) from SalesOrder s where
// s.createdAt=:startDate")
// public int findMaxOutletIdByOrderOutletId(@Param("startDate") LocalDate
// startDate);

// @Query("select MIN (s.outlet.outletId) from SalesOrder s where
// s.createdAt=:startDate")
// public int findMinOutletIdByOrderOutletId(@Param("startDate") LocalDate
// startDate);

// @Query("SELECT s.outlet.outletId,s.outlet.name, COUNT(s.outlet.outletId) AS
// outletCount FROM SalesOrder s where s.createdAt between :startDate and
// :endDate GROUP BY s.outlet.outletId,s.outlet.name ORDER BY outletCount DESC
// ")
// List<Object[]> findOrderedMostAndMinOutlet(@Param("startDate") LocalDate
// startDate,
// @Param("endDate") LocalDate endDate);

// @Query("SELECT s FROM SalesOrder s WHERE s.assignedSalesOfficer.assignedId =
// :soId AND s.createdAt BETWEEN :startDate AND :endDate")
// List<SalesOrder> findByCreatedAtBetweenAndAssignedSalesOfficer(
// @Param("startDate") LocalDate startDate,
// @Param("endDate") LocalDate endDate,
// @Param("soId") int soId);

// @Query("select s.deliveredProduct.productItem.sku from SalesOrder s where
// s.createdAt=:date and s.deliveredProduct.id=:id")
// List<SalesOrder> findBySkyWithDateAndSoWise(@Param("date") LocalDate date,
// @Param("id") int id);

// List<SalesOrder> findByCreatedAtBetween(LocalDate startDate, LocalDate
// endDate);

// @Query("select s from SalesOrder s where s.createdAt=:createdAt and
// s.assignedSalesOfficer.assignedId=:id")
// List<SalesOrder> findByCreatedAtAndAssignedSalesOfficer(@Param("createdAt")
// LocalDate createdAt,
// @Param("id") int id);

// @Query("select s.deliveredProduct.productItem.product.category from
// SalesOrder s where s.route.routeId = :id AND s.createdAt=:startDate ")
// List<Object[]> findByCreatedAtAndRouteWise(@Param("startDate") LocalDate
// startDate, @Param("id") int id);

// @Query("SELECT s.createdAt from SalesOrder s where
// s.deliveredProduct.productItem.product.category.id=?1")
// List<Object[]> achievementDateByCategory(int id);

// @Query("SELECT s from SalesOrder s where
// s.deliveredProduct.productItem.product.category.id=?1")
// List<Object[]> salesAchievementByCategory(int id);

// @Query("select s.outlet.name, s.outlet.route.name from SalesOrder s where
// s.createdAt=?1")
// List<Object[]> findRouteAndOutletByCreatedAt(LocalDate date);

// }
