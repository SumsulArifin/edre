package com.tkgroupbd.pusti.api.data.repositories.primarysales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ProductDistributionSchedules;

@Repository
public interface ProductDistributionSchedulesRepository extends JpaRepository<ProductDistributionSchedules, Integer> {
}
