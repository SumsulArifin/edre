package com.tkgroupbd.pusti.api.data.repositories.primarysales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovePrimaryOrders;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApprovePrimaryOrderRepository extends JpaRepository<ApprovePrimaryOrders, Integer> {

    @Query("select a from ApprovePrimaryOrders a where a.primaryOrderApproval.primaryOrderId=?1")
    public List<ApprovePrimaryOrders> findApprovePrimaryOrdersByPrimaryOrderApprovalId(int id);

    @Query("select a from ApprovePrimaryOrders a where a.primaryOrderApproval.status=true and a.createdAt BETWEEN :startDate AND :endDate")
    public List<Object[]> findApprovePrimaryOrdersByCurrentDate(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
