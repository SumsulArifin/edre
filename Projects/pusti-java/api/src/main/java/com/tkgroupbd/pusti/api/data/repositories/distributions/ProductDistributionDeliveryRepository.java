package com.tkgroupbd.pusti.api.data.repositories.distributions;

import com.tkgroupbd.pusti.api.data.models.entity.distributions.ProductDistributionDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDistributionDeliveryRepository extends JpaRepository<ProductDistributionDelivery,Integer> {
}
