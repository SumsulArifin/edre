package com.tkgroupbd.pusti.api.data.payloads.dto.orders.secondary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.orders.secondary.DeliveredProduct;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryType;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CollectionProductRequest extends BaseEntity {
    private int scheduleOutlet;
    private int visitedOutlet;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom receivedFrom;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    private ProductItem productItem;
    private Outlet outletAmendment;
    private AssignedSalesOfficer assignedSalesOfficer;
    private DeliveredProduct deliveredProduct;
}
