package com.tkgroupbd.pusti.api.data.models.entity.salesorders;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.*;
import com.tkgroupbd.pusti.api.data.models.entity.orders.secondary.DeliveredProduct;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "salesOrder")
public class SalesOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesOrderId;
    private String salesOrganizationId;
    private boolean orderStatus;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

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
    @JoinColumn(name = "zoneId")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "deliveredId", nullable = true)
    private DeliveredProduct deliveredProduct;

}
