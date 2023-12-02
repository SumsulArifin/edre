package com.tkgroupbd.pusti.api.data.repositories.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.ProductSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSettingsRepository extends JpaRepository<ProductSettings, Integer> {
}
