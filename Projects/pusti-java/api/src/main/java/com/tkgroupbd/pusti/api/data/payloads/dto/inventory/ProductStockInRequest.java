package com.tkgroupbd.pusti.api.data.payloads.dto.inventory;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductStockInRequest extends BaseEntity {

    private String skuName;
    private int quantity;
    private String remarks;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom receivedFrom;
    private Product products;
    private Distributor distributor;
    private Factory factory;
}
