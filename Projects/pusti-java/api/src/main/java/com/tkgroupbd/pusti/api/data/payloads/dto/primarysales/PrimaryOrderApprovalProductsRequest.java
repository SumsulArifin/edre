package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovals;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryOrderApprovalProductsRequest extends BaseEntity {
    private long productId;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    private int quantity;
    private long pricePerUnit;
    private long superDistributorId;

    private Product products;
    private PrimaryOrderApprovals primaryOrderApprovals;
}
