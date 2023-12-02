package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.RouteSalesOfficer;

import java.util.List;

@Repository
public interface RouteSalesOfficerRepository extends JpaRepository<RouteSalesOfficer, Integer> {

    
}
