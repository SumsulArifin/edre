package com.tkgroupbd.pusti.api.data.repositories.primarysales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PurchaseOrderItems;

@Repository
public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, Integer> {
}
