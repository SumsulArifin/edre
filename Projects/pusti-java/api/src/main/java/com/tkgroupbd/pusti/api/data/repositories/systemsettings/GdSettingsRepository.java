package com.tkgroupbd.pusti.api.data.repositories.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.GdSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdSettingsRepository extends JpaRepository<GdSettings,Integer> {
}
