package com.tkgroupbd.pusti.api.data.models.entity.factory;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
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
@Table(name = "products_stock_in")
public class ProductStockIn extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private String skuName;
    private int quantity;
    private String remarks;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom receivedFrom;

    @ManyToOne
    @JoinColumn(name = "factoryId")
    private Factory factory;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

}
