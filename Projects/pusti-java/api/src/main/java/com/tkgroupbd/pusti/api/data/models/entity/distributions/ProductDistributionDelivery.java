package com.tkgroupbd.pusti.api.data.models.entity.distributions;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ProductDistributionSchedules;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_distribution_deliveries")

public class ProductDistributionDelivery extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sku;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom productSourceType;
    @Enumerated(EnumType.ORDINAL)
    private ReceivedFrom productSourceId;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom recipientType;
    private int recipientId;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = true)
    private PurchaseOrders purchaseOrders;

    @ManyToOne
    @JoinColumn(name = "scheduleId", nullable = true)
    private ProductDistributionSchedules productDistributionSchedules;

}
