package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryOrderApprovalProcessRequest extends BaseEntity {
    private String comments;
    private int nextApprove;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private ApprovalCommittee approvalCommittee;
    private PurchaseOrders orders;

}
