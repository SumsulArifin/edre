package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

    @Query(value = "select zn.* from Zone zn where zn.region.regionId=?1", nativeQuery = true)
    public List<Zone> findZoneByRegionId(int id);

    @Query("SELECT z FROM Zone z WHERE LOWER(z.zoneName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY z.zoneName ASC")
    List<Zone> findZonesByZoneNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);


    @Query("select z from Zone z where LOWER(z.zoneName) LIKE LOWER(CONCAT('%', :name, '%')) or LOWER(z.region.regionName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Zone> findAllZoneByZoneNameOrRegionName(@Param("name") String name);





}
