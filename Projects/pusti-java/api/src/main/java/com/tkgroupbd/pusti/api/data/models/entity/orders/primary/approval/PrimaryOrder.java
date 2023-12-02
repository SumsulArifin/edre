package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "primaryOrders")
public class PrimaryOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int primaryOrderId;
    private String title;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private boolean hasDeliveryApproval;
    private boolean isDeliveryApprovalApproved;

    @ManyToOne
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name = "depotId")
    private Depot depot;

    @ManyToOne
    @JoinColumn(name = "currentApprover")
    private User user;
}
