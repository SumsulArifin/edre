package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.enums.StorageUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PurchaseOrderItems")
public class PurchaseOrderItems extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long productId;

    @Enumerated(EnumType.STRING)
    private StorageUnit storageUnit;
    private int quantity;
    private int pricePerUnit;
    private int superDistributorId;
    @ManyToOne
    @JoinColumn(name = "productSku")
    private Product products;
    @ManyToOne
    @JoinColumn(name = "ordersId")
    private PurchaseOrders orders;

}
