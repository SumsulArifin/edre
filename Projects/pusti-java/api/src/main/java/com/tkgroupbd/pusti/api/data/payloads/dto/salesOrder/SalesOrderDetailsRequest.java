package com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class SalesOrderDetailsRequest extends BaseEntity {
    private int id;
    private String sku;
    private double distribution_price;
    private double trade_price;
    private double ordered_quantity;
    private double ordered_quantity_free;
    private double ordered_quantity_free_bundle;
    private double trade_price_discount;
    private double outlet_discount;
    private double receipt_amount_discount;
    private String remarks;
    private String payment_mode;
    private Category category;
    private Product product;
    private ProductItem productItem;
    private AssignedSalesOfficer assignedSalesOfficer;
    private SalesOrder salesOrder;

}
