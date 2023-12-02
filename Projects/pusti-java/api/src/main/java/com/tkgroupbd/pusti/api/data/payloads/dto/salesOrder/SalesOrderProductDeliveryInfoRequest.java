package com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesOrderProductDeliveryInfoRequest extends BaseEntity {
    private String product_sku;
    private String delivered_quantity;
    private SalesOrderDetails salesOrderDetails;
}
