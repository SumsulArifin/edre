package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query("select r from Region r inner join r.division d on r.division.divisionId = d.divisionId")
    public List<Region> findAllRegen();

    @Query("select r from Region r where r.division.divisionId=?1")
    public List<Region> findRegionByDivisionId(int id);


    @Query("SELECT r FROM Region r WHERE LOWER(r.regionName) LIKE LOWER(CONCAT('%', :name, '%'))  ORDER BY r.regionName ASC")
    List<Region> findRegionByRegionNameContainingIgnoreCase(@Param("name") String name);


    @Query("select r from Region r where LOWER(r.regionName) LIKE LOWER(CONCAT('%', :name, '%')) or LOWER(r.division.divisionName) LIKE LOWER(CONCAT('%', :name, '%')) or LOWER(r.division.national.nationalName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Region> findAllRegionByRegionNameOrDivisionNameOrNationalName(@Param("name") String name);

}
