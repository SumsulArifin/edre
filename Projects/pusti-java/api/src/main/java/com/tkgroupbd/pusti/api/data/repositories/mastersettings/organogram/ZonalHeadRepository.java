package com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.ZonalHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonalHeadRepository extends JpaRepository<ZonalHead, Integer> {

}
