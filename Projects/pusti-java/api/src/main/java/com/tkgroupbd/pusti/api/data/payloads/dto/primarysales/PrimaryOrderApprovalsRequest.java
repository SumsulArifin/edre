package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovalProductAmendmentLogs;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalStatus;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryOrderApprovalsRequest extends BaseEntity {
    private String approvalName;
    private String offerNote;
    private int superDistributorId;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @Enumerated(EnumType.STRING)
    private ApprovalType approvalType;
    private ApprovalCommittee approvalCommittee;
    private Distributor distributor;
    private List<PrimaryOrderApprovalProductsRequest> primaryOrderApprovalProductsRequests;
    private List<ApprovalProductAmendmentLogs> approvalProductAmendmentLogsRequests;
}
