package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import com.tkgroupbd.pusti.api.data.models.enums.StorageUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryOrderProductDetailsRequest extends BaseEntity {
    private int productSkuNumber;
    @Enumerated(EnumType.STRING)
    private StorageUnit storageUnit;
    private int quantity;
    private double distributionPrice;
    private double grossAmount;
    private double netAmount;
    private PrimaryOrder primaryOrders;
}
