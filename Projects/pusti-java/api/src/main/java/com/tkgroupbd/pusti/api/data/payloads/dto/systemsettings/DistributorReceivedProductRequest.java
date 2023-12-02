package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DistributorReceivedProductRequest extends BaseEntity {

    private String receivedYearMonth;
    private int receivedQuantity;
    private double distributorPrice;
    private double receivedFree;
    private double receivedAdjusted;
    private double receivedFreeAdjusted;
    private double blockedStock;
    private String operationType;
    private Product products;
    private Distributor distributor;

}
