package com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOfficerRepository extends JpaRepository<SalesOfficer, Integer> {
}
