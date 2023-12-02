package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletType;

@Repository
public interface OutletTypeRepository extends JpaRepository<OutletType, Integer> {

}
