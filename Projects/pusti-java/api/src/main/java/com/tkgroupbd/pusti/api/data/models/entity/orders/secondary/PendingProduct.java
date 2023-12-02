package com.tkgroupbd.pusti.api.data.models.entity.orders.secondary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovals;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryType;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "pending_Products")
@EqualsAndHashCode(callSuper = false)
public class PendingProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pendingId;
    private String sku;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @Enumerated(EnumType.STRING)
    private ReceivedFrom receivedFrom;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @ManyToOne()
    @JoinColumn(name = "approvedOrderId", nullable = true)
    private PrimaryOrderApprovals primaryOrderApprovals;

    @ManyToOne
    @JoinColumn(name = "productItemId", nullable = true)
    private ProductItem productItem;

    @ManyToOne
    @JoinColumn(name = "outletId", nullable = true)
    private Outlet outletAmendment;

    @ManyToOne
    @JoinColumn(name = "soId", nullable = true)
    private AssignedSalesOfficer assignedSalesOfficer;
}
