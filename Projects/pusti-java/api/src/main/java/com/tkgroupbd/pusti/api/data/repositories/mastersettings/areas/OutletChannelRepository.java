package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletChannel;

@Repository
public interface OutletChannelRepository extends JpaRepository<OutletChannel, Integer> {

}
