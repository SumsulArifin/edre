package com.tkgroupbd.pusti.api.data.repositories.orders.secondary;
// package com.tkgroupbd.pusti.api.data.repositories.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.secondaryorders.DeliveredProduct;
// import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface DeliveredProductRepository extends
// JpaRepository<DeliveredProduct, Integer> {

// @Query("select d from DeliveredProduct d where d.createdAt=?1")
// List<DeliveredProduct> findByCreatedAt(LocalDate createdAt);

// @Query("SELECT d FROM DeliveredProduct d WHERE d.createdAt BETWEEN :startDate
// AND :endDate")
// List<DeliveredProduct> findByCreatedAtBetween(@Param("startDate") LocalDate
// startDate,
// @Param("endDate") LocalDate endDate);

// @Query("select d from DeliveredProduct d where d.createdAt BETWEEN :startDate
// AND :endDate and d.distributor.distributor_id=:id")
// List<DeliveredProduct>
// findMonthlyDeliveredReportByDistributor(@Param("startDate") LocalDate
// startDate,
// @Param("endDate") LocalDate endDate, @Param("id") int id);

// @Query("select d.productItem.product.productId,d.productItem.product.name
// from DeliveredProduct d where
// d.assignedSalesOfficer.distributor.distributor_id=?1")
// List<Object[]> findDeliveredProductByDistributor(int distributor_id);

// // @Query("select d from DeliveredProduct d where d.createdAt BETWEEN
// :startDate
// // AND :endDate and d.distributor.distributor_id=:id")
// // List<DeliveredProduct>
// // findMonthlyDeliveredReportByDistributor(@Param("startDate") LocalDate
// // startDate,
// // @Param("endDate") LocalDate endDate, @Param("id") int id);

// @Query("select d from DeliveredProduct d where d.createdAt=:startDate and
// d.productItem.sku=:sku")
// List<DeliveredProduct>
// findDeliveredProductByCreatedAtAndSku(@Param("startDate") LocalDate
// startDate,
// @Param("sku") String sku);

// @Query("select count (distinct d.productItem.product.category.id) from
// DeliveredProduct d")
// public int findTotalDeliveredCategory();

// // @Query("select d.productItem.product.productId,d.productItem.product.name
// // from DeliveredProduct d where
// // d.assignedSalesOfficer.distributor.distributor_id=?1")
// // List<Object[]> findDeliveredProductByDistributor(int distributor_id);

// @Query("select d from DeliveredProduct d where d.createdAt BETWEEN :startDate
// AND :endDate and d.deliveryStatus=:deliveryStatus")
// List<Object[]> findWeeklyDeliveredReport(DeliveryStatus deliveryStatus,
// @Param("startDate") LocalDate startDate,
// @Param("endDate") LocalDate endDate);

// @Query("SELECT d.deliveryStatus FROM DeliveredProduct d WHERE d.createdAt
// BETWEEN :startDate AND :endDate")
// List<DeliveredProduct>
// findDeliveryStatusByCreatedAtBetween(@Param("startDate") LocalDate startDate,
// @Param("endDate") LocalDate endDate);

// @Query("select d from DeliveredProduct d where
// d.productItem.product.category.id=?1")
// List<Object[]> findDeliveredProductByCategory(int id);

// @Query("select d from DeliveredProduct d where d.createdAt=:date and
// d.distributor.distributor_id=:id")
// List<DeliveredProduct> findDbDailyReceivedProduct(@Param("date") LocalDate
// date, @Param("id") int id);

// }
