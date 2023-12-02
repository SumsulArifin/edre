package com.tkgroupbd.pusti.api.data.repositories.orders.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.secondary.CollectionProduct;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CollectionProductRepository extends JpaRepository<CollectionProduct, Integer> {

        @Query("select c from CollectionProduct  c where c.assignedSalesOfficer.assignedId=?1")
        List<CollectionProduct> findBySoId(int id);

        @Query("select c from CollectionProduct  c where c.outletAmendment.id=?1")
        List<CollectionProduct> findByOutletAmendmentId(int id);

        List<CollectionProduct> findByCreatedAt(LocalDate createdAt);

        List<CollectionProduct> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

        @Query("select  c from CollectionProduct c where c.createdAt=:createdAt and c.productItem.sku=:sku ")
        List<CollectionProduct> findCollectionProductByCreatedAtAndSku(@Param("createdAt") LocalDate date,
                        @Param("sku") String sku);

        @Query("SELECT c FROM CollectionProduct c WHERE c.assignedSalesOfficer.assignedId = :soId AND c.createdAt BETWEEN :startDate AND :endDate")
        List<CollectionProduct> findByCreatedAtBetweenAndAssignedSalesOfficer(
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("soId") int soId);

        List<CollectionProduct> findByOutletAmendment_Route_Zone_ZoneId(int zoneId);

        List<CollectionProduct> findByOutletAmendment_Route_Zone_Region_RegionId(int regionId);

}
