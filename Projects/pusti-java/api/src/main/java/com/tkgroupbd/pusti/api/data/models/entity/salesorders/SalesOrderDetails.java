package com.tkgroupbd.pusti.api.data.models.entity.salesorders;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "salesOrderDetails")
public class SalesOrderDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "salesOrderId")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "productItemId", nullable = true)
    private ProductItem productItem;

}
