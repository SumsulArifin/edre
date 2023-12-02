package com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrganizationRepository extends JpaRepository<SalesOrganization, Integer> {
    List<SalesOrganization> findBySalesOrgNameContaining(String name);
}
