package com.tkgroupbd.pusti.api.data.repositories.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.AlternateChannelSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternateChannelSettingsRepository extends JpaRepository<AlternateChannelSettings, Integer> {

}
