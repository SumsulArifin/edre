package com.tkgroupbd.pusti.api.data.models.entity.salesorders;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "salesOrderProductDeliveryInfo")
public class SalesOrderProductDeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String product_sku;
    private String delivered_quantity;

    @ManyToOne
    @JoinColumn(name = "salesOrderDetailsId")
    private SalesOrderDetails salesOrderDetails;
}
