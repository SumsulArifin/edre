package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer> {
    @Query("SELECT d FROM Division d WHERE d.divisionName LIKE %:divisionName% ESCAPE '!' ORDER BY d.divisionName ASC")
    List<Division> findDivisionByDivisionNameContainingIgnoreCase(@Param("divisionName") String divisionName);

}
