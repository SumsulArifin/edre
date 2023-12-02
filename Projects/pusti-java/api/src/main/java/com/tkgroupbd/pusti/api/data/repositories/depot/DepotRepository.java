package com.tkgroupbd.pusti.api.data.repositories.depot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Integer> {
    @Query("select  d from  Depot d where lower(d.depotName) like lower(concat('%',:name,'%' )) ESCAPE '!' order by d.depotName ASC ")
    List<Depot> findByNameContaining(@Param("name") String name);
}
