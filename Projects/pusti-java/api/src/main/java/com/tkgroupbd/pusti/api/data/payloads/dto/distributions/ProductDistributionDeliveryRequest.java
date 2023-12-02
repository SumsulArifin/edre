package com.tkgroupbd.pusti.api.data.payloads.dto.distributions;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ProductDistributionSchedules;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDistributionDeliveryRequest extends BaseEntity {
    private String sku;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom productSourceType;
    @Enumerated(EnumType.ORDINAL)
    private ReceivedFrom productSourceId;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom recipientType;
    private int recipientId;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private PurchaseOrders purchaseOrders;
    private ProductDistributionSchedules productDistributionSchedules;
}
