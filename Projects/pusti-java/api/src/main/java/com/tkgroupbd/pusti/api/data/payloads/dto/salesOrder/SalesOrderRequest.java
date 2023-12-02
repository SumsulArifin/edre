package com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.*;
import com.tkgroupbd.pusti.api.data.models.entity.orders.secondary.DeliveredProduct;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesOrderRequest extends BaseEntity {
    private String salesOrganizationId;
    private Distributor distributor;
    private AssignedSalesOfficer assignedSalesOfficer;
    private Route route;
    private Outlet outlet;
    private boolean orderStatus;
    private Zone zone;
    private Region region;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private DeliveredProduct deliveredProduct;
    private List<SalesOrderDetailsRequest> salesOrderDetailsRequests;

}
