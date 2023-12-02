package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovePrimaryOrders;
import com.tkgroupbd.pusti.api.data.models.enums.OrderType;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "purchase_orders")
public class PurchaseOrders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordersId;
    private String name;
    private String superDistributorId;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private String isApprovalRequired;
    private String approvalStatus;

    @ManyToOne
    @JoinColumn(name = "depotId")
    private Depot depot;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name = "id")
    private ApprovePrimaryOrders approvePrimaryOrders;

}
