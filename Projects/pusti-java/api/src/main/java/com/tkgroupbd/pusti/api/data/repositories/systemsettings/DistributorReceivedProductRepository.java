package com.tkgroupbd.pusti.api.data.repositories.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributorReceivedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributorReceivedProductRepository extends JpaRepository<DistributorReceivedProduct, Integer> {
}
