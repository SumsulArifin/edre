package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.ApprovalCommitteeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ApprovalCommitteeService {
    public ApprovalCommittee saveApprovalCommittee(ApprovalCommitteeDTO approvalCommitteeDTO);

    public MessageResponse updateApprovalCommittee(int id, ApprovalCommitteeDTO approvalCommitteeDTO);

    public List<ApprovalCommittee> getAllApprovalCommittee();

    public ApprovalCommittee getApprovalCommitteeDetailsById(int committeeId);

    public Optional<ApprovalCommittee> updateApprovalCommitteeStatus(int id,
            ApprovalCommitteeDTO approvalCommitteeRequest);
}
