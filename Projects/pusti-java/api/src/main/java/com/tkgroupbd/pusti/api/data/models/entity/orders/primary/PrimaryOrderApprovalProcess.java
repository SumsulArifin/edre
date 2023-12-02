package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalStatus;
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
@Table(name = "PrimaryOrderApprovalProcess")
public class PrimaryOrderApprovalProcess extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int primaryOrderId;
    private String comments;
    private int nextApprove;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @ManyToOne
    @JoinColumn(name = "committeeId")
    private ApprovalCommittee approvalCommittee;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private PurchaseOrders orders;
}
