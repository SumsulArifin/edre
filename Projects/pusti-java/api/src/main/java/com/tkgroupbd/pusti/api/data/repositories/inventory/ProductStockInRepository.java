// package com.tkgroupbd.pusti.api.data.repositories.inventory;

// import com.tkgroupbd.pusti.api.data.models.entity.inventory.ProductStockIn;
// import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// @Repository
// public interface ProductStockInRepository extends
// JpaRepository<ProductStockIn, Integer> {
// List<ProductStockIn> findAllByOrderByCreatedAtDesc();

// List<ProductStockIn> findAllByCreatedAtContaining(String createdAt);

// List<ProductStockIn> findByReceivedFrom(ReceivedFrom receivedFrom);

// @Query("select p.quantity,p.products,p.skuName from ProductStockIn p where
// p.distributor.distributor_id=:distributor_id and p.createdAt between
// :startDate and :endDate")
// List<Object[]> findSOCKByDistributorIdWithDate(int distributor_id,
// @Param("startDate") LocalDate startDate,
// @Param("endDate") LocalDate endDate);

// @Query("select p from ProductStockIn p where p.skuName=?1")
// public Optional<ProductStockIn> findBySkuName(String sku);

// @Query("select p.quantity,p.products from ProductStockIn p where
// p.distributor.distributor_id=?1")
// List<Object[]> findSOCKByDistributorId(int distributor_id);

// @Query("select p.quantity,p.products from ProductStockIn p where
// p.distributor.distributor_id=:distributor_id and p.createdAt between
// :startDate and :endDate")
// List<Object[]> findStockByDistributorIdWithDate(int distributor_id,
// @Param("startDate") LocalDate startDate,
// @Param("endDate") LocalDate endDate);

// }
