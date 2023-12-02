package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("SELECT d FROM District d WHERE d.districtName LIKE %:districtName% ESCAPE '!' ORDER BY d.districtName ASC")
    List<District> findByDistrictNameContaining(String districtName);
}
