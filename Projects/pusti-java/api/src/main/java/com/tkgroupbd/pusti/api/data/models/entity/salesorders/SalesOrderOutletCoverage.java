package com.tkgroupbd.pusti.api.data.models.entity.salesorders;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "salesOrderOutletCoverage")
public class SalesOrderOutletCoverage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name = "assignedId")
    private AssignedSalesOfficer assignedSalesOfficer;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "outletId")
    private Outlet outlet;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

}
