package com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SalesTargetRepository extends JpaRepository<SalesTarget,Integer> {
    @Query("select t from SalesTarget t where TRIM(LOWER(t.productItem.sku)) = TRIM(LOWER(:sku)) and t.createdAt between :startDate and :endDate")
    List<SalesTarget> findTargetSalesBySkuAndCreatedAtBetween(
            @Param("sku") String sku,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


    @Query("SELECT t FROM SalesTarget t WHERE TRIM(LOWER(t.productItem.sku)) = TRIM(LOWER(:sku))")
    List<SalesTarget> findTargetSalesBySku(@Param("sku") String sku);

    public List<SalesTarget>findSalesTargetByAndAssignedSalesOfficer_Route_RouteId(int routeId);


}
