package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutletRepository extends JpaRepository<Outlet, Integer> {

    @Query("select o from Outlet o where o.route.routeId=?1")
    public List<Outlet> findByRoute(int id);

    @Query("SELECT o FROM Outlet o WHERE LOWER(o.outletName) LIKE LOWER(CONCAT('%', :outletName, '%'))  ORDER BY o.outletName ASC")
    List<Outlet> findByOutletName(@Param("outletName") String outletName);

    @Query("select o from Outlet o where " +
            "LOWER(o.outletName) LIKE LOWER(CONCAT('%', :name, '%'))" +
            "or LOWER(o.route.routeName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "or LOWER(o.address) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "or LOWER(o.contactPerson) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "order by (o.outletName) asc")
    List<Outlet> findAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(@Param("name") String name);

}
