package com.tkgroupbd.pusti.api.data.repositories.factory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer> {
    @Query("SELECT r FROM Factory r WHERE LOWER(r.factoryName) = LOWER(:name)")
    Optional<Factory> findByFactoryNameIgnoreCase(@Param(("name")) String name);
    @Query("SELECT f FROM Factory f WHERE LOWER(f.factoryName) LIKE LOWER(CONCAT('%', :factoryName, '%'))  ORDER BY f.factoryName ASC")
    List<Factory> findAllByFactoryNameContainingIgnoreCase(@Param("factoryName") String factoryName);

    @Query("SELECT f FROM Factory f WHERE LOWER(f.factoryName) = LOWER(:factoryName)")
    Optional<Factory> findByFactoryName(@Param("factoryName") String factoryName);

    @Query("SELECT f FROM Factory f JOIN f.assignDepots ad JOIN ad.depot d WHERE d.depotId = :depotId")
    List<Factory> findFactoriesByDepotId(@Param("depotId") int depotId);

    @Query("SELECT  f FROM Factory f " +
            "LEFT JOIN f.assignDepots ad " +
            "LEFT JOIN ad.depot d " +
            "WHERE LOWER(f.factoryName) LIKE LOWER(CONCAT('%', :fieldName, '%')) " +
            "OR LOWER(d.depotName) LIKE LOWER(CONCAT('%', :fieldName, '%')) ORDER BY f.factoryName ASC")
    List<Factory> searchFactoriesAndDepots(@Param("fieldName") String fieldName);

}
