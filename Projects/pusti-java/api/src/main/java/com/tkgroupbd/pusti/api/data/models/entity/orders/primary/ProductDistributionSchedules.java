package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.enums.PDSStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ProductDistributionSchedules")
@Entity
public class ProductDistributionSchedules extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String SKU;
    private int plannedQuantity;
    private int deliveredQuantity;
    private String comments;
    @Enumerated(EnumType.STRING)
    private PDSStatus pdsStatus;
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "ordersId")
    private PurchaseOrders purchaseOrders;
}
