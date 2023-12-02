package com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesOrderOutletCoverageRequest extends BaseEntity {
    private int quantity;
    private Distributor distributor;
    private AssignedSalesOfficer assignedSalesOfficer;
    private Route route;
    private Outlet outlet;
    private Brand brand;
}
