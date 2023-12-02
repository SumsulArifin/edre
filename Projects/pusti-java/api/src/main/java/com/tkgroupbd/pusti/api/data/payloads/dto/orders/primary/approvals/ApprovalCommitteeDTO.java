package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommitteeMember;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalCommitteeDTO extends BaseEntity {
    private Integer committeeId;
    private String name;
    private List<ApprovalCommitteeMember> approvalCommitteeMemberList;
}
