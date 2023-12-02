package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.enums.PDSStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDistributionSchedulesRequest extends BaseEntity {
    private String SKU;
    private int plannedQuantity;
    private int deliveredQuantity;
    private String comment;
    @Enumerated(EnumType.STRING)
    private PDSStatus pdsStatus;
    private Date deliveryDate;
    private PurchaseOrders purchaseOrders;

}
