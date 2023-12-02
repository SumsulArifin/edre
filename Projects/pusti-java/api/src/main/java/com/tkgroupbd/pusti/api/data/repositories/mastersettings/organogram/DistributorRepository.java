package com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Integer> {
    @Query("SELECT d FROM Distributor  d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :distributorName, '%')) ORDER BY d.name asc")
    List<Distributor> findDistributorByDistributorNameContainingIgnoreCase(String distributorName);


    @Query("SELECT d FROM Distributor  d WHERE LOWER(d.zone.zoneName) LIKE LOWER(CONCAT('%',:zoneName,'%')) ORDER BY d.name asc")
    List<Distributor> findDistributorByZoneNameContainingIgnoreCase(String zoneName);

    @Query
    List<Distributor> findDistributorByUpazilaUpazilaId(int upazilaId);
}
