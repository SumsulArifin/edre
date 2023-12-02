package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.orders.secondary.PendingProduct;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RouteRequest;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RouteRetrievalRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
  @Query("SELECT r FROM Route r WHERE LOWER(r.routeName) LIKE LOWER(CONCAT('%', :routeName, '%'))  ORDER BY r.routeName ASC")
  public List<Route> findAllByRouteNameContainingIgnoreCase(@Param("routeName") String routeName);

  @Query("select r from Route r where lower( r.routeName)=?1")
  public Optional<Route> findByRouteName(String routeName);

  @Query("select r from Route r where " +
          "LOWER(r.routeName) LIKE LOWER(CONCAT('%', :name, '%'))" +
          "OR LOWER(r.distributor.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
          "OR LOWER(r.zone.zoneName) LIKE LOWER(CONCAT('%', :name, '%')) " +
          "OR LOWER(r.zone.region.regionName) LIKE LOWER(CONCAT('%', :name, '%')) " +
          "OR LOWER(r.zone.region.division.divisionName) LIKE LOWER(CONCAT('%', :name, '%'))" +
          "order by (r.routeName) asc")
  List<Route> findAllDbNameOrZoneNameOrRegionNameOrDivisionName(@Param("name") String name);



}
