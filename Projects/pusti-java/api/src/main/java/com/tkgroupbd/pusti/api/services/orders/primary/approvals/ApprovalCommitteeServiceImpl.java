package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommitteeMember;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.ApprovalCommitteeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.ApprovalCommitteeRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApprovalCommitteeServiceImpl implements ApprovalCommitteeService {
    @Autowired
    private ApprovalCommitteeRepository approvalCommitteeRepository;

    @Override
    public ApprovalCommittee saveApprovalCommittee(ApprovalCommitteeDTO approvalCommitteeDTO) {

        ApprovalCommittee approvalCommittee = new ApprovalCommittee();

        approvalCommittee.setCommitteeName(approvalCommitteeDTO.getName());
        approvalCommittee.setStatus(approvalCommitteeDTO.isStatus());
        approvalCommittee.setApprovalCommitteeMemberList(approvalCommitteeDTO.getApprovalCommitteeMemberList());

        // Set ApprovalCommitteeMember with the ApprovalCommittee
        for (ApprovalCommitteeMember child : approvalCommittee.getApprovalCommitteeMemberList()) {
            child.setApprovalCommittee(approvalCommittee);
        }

        // Save the ApprovalCommittee
        approvalCommittee = approvalCommitteeRepository.save(approvalCommittee);

        return approvalCommittee;
    }

    @Override
    public MessageResponse updateApprovalCommittee(int committeeId, ApprovalCommitteeDTO approvalCommitteeDTO) {

        Optional<ApprovalCommittee> result = approvalCommitteeRepository.findById(committeeId);
        if (result.isPresent()) {
            ApprovalCommittee approvalCommittee = result.get();

            approvalCommittee.setCommitteeName(approvalCommitteeDTO.getName());
            approvalCommittee.setStatus(approvalCommitteeDTO.isStatus());
            approvalCommittee.setApprovalCommitteeMemberList(approvalCommitteeDTO.getApprovalCommitteeMemberList());

            // Set ApprovalCommitteeMember with the ApprovalCommittee
            for (ApprovalCommitteeMember child : approvalCommittee.getApprovalCommitteeMemberList()) {
                child.setApprovalCommittee(approvalCommittee);
            }

            approvalCommitteeRepository.save(approvalCommittee);

            return new MessageResponse(Message.SUCCESS_CREATION);
        }
        return new MessageResponse(Message.FAILED_CREATION);
    }

    @Override
    public List<ApprovalCommittee> getAllApprovalCommittee() {
        return approvalCommitteeRepository.findAll();
    }

    @Override
    public ApprovalCommittee getApprovalCommitteeDetailsById(int committeeId) {
        return approvalCommitteeRepository.getApprovalCommitteeDetailsById(committeeId);
    }

    @Override
    public Optional<ApprovalCommittee> updateApprovalCommitteeStatus(int committeeId,
            ApprovalCommitteeDTO approvalCommitteeDTO) {
        Optional<ApprovalCommittee> result = approvalCommitteeRepository.findById(committeeId);
        if (result.isPresent()) {
            ApprovalCommittee approvalCommittee = result.get();
            approvalCommittee.setStatus(approvalCommitteeDTO.isStatus());
            approvalCommitteeRepository.save(approvalCommittee);
        } else {
            throw new ResourceNotFoundException("ApprovalCommittee", "committeeId",
                    committeeId);
        }

        return result;
    }
}