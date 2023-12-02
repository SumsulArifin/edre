//package com.tkgroupbd.pusti.api.services.primarysales;
//
//import com.tkgroupbd.pusti.api.data.models.entity.employee.Employee;
//import com.tkgroupbd.pusti.api.data.models.entity.employee.EmployeeEducation;
//import com.tkgroupbd.pusti.api.data.models.entity.primaryorders.ApprovalCommittee;
//import com.tkgroupbd.pusti.api.data.models.entity.primaryorders.ApprovalCommitteeMember;
//import com.tkgroupbd.pusti.api.data.payloads.requests.employee.EmployeeRequest;
//import com.tkgroupbd.pusti.api.data.payloads.requests.primaryorders.ApprovalCommitteeMemberRequest;
//import com.tkgroupbd.pusti.api.data.payloads.requests.primaryorders.ApprovalCommitteeRequest;
//import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
//import com.tkgroupbd.pusti.api.data.repositories.primaryorders.ApprovalCommitteeMemberRepository;
//import com.tkgroupbd.pusti.api.data.repositories.primaryorders.ApprovalCommitteeRepository;
//import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
//import com.tkgroupbd.pusti.api.services.primarysales.ApprovalCommitteeService;
//import com.tkgroupbd.pusti.api.utils.constant.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ApprovalCommitteeServiceImpl implements ApprovalCommitteeService {
//    @Autowired
//    private ApprovalCommitteeRepository approvalCommitteeRepository;
//    @Autowired
//    private ApprovalCommitteeMemberRepository approvalCommitteeMemberRepository;
//
//    @Override
//    public ApprovalCommittee saveApprovalCommitteeWithApprovalCommitteeMember(
//            ApprovalCommitteeRequest approvalCommitteeRequest) {
//        // Convert DiscountDTO to ApprovalCommittee entity
//        ApprovalCommittee approvalCommittee = new ApprovalCommittee();
//        approvalCommittee.setName(approvalCommitteeRequest.getName());
//        approvalCommittee.setStatus(approvalCommitteeRequest.isStatus());
//        approvalCommittee.setCreatedBy(approvalCommitteeRequest.getCreatedBy());
//        approvalCommittee.setUpdatedBy(approvalCommitteeRequest.getUpdatedBy());
//        approvalCommittee.setCreatedAt(approvalCommitteeRequest.getCreatedAt());
//        approvalCommittee.setUpdatedAt(approvalCommitteeRequest.getUpdatedAt());
//        approvalCommittee.setDeletedAt(approvalCommitteeRequest.getDeletedAt());
//
//        // Save the ApprovalCommittee entity
//        approvalCommittee = approvalCommitteeRepository.save(approvalCommittee);
//
//        // Loop through each DiscountDetailDTO and convert to DiscountDetail entity
//        for (ApprovalCommitteeMemberRequest approvalCommitteeMemberRequest : approvalCommitteeRequest
//                .getApprovalCommitteeMemberRequests()) {
//            ApprovalCommitteeMember approvalCommitteeMember = new ApprovalCommitteeMember();
//            approvalCommitteeMember.setApprovalCommittee(approvalCommittee);
//            approvalCommitteeMember.setSequenceNumber(approvalCommitteeMemberRequest.getSequenceNumber());
//            approvalCommitteeMember.setCreatedBy(approvalCommitteeMemberRequest.getCreatedBy());
//            approvalCommitteeMember.setEmployee(approvalCommitteeMemberRequest.getEmployee());
//            approvalCommitteeMember.setUpdatedBy(approvalCommitteeMemberRequest.getUpdatedBy());
//            approvalCommitteeMember.setCreatedAt(approvalCommitteeMemberRequest.getCreatedAt());
//            approvalCommitteeMember.setUpdatedAt(approvalCommitteeMemberRequest.getUpdatedAt());
//            approvalCommitteeMember.setDeletedAt(approvalCommitteeMemberRequest.getDeletedAt());
//
//            // Save the DiscountDetail entity
//            approvalCommitteeMemberRepository.save(approvalCommitteeMember);
//        }
//
//        return approvalCommittee;
//    }
//
//    @Override
//    public MessageResponse updateApprovalCommittee(int committeeId, ApprovalCommitteeRequest approvalCommitteeRequest) {
//        Optional<ApprovalCommittee> result = approvalCommitteeRepository.findById(committeeId);
//        List<ApprovalCommitteeMember> approvalCommitteeMembers = new ArrayList<>();
//
//        if (result.isPresent()) {
//            ApprovalCommittee approvalCommittee = result.get();
//
//            approvalCommittee.setName(approvalCommitteeRequest.getName());
//            approvalCommittee.setStatus(approvalCommitteeRequest.isStatus());
//            approvalCommittee.setCreatedBy(approvalCommitteeRequest.getCreatedBy());
//            approvalCommittee.setUpdatedBy(approvalCommitteeRequest.getUpdatedBy());
//            approvalCommittee.setCreatedAt(approvalCommitteeRequest.getCreatedAt());
//            approvalCommittee.setUpdatedAt(approvalCommitteeRequest.getUpdatedAt());
//            approvalCommittee.setDeletedAt(approvalCommitteeRequest.getDeletedAt());
//
//             approvalCommitteeRepository.save(approvalCommittee);
//
//
//
//        return new MessageResponse(Message.SUCCESS_CREATION);
//    }
//
//    @Override
//    public List<ApprovalCommittee> getAllApprovalCommittee() {
//        return approvalCommitteeRepository.findAll();
//    }
//
//    @Override
//    public ApprovalCommitteeMember getApprovalCommitteeDetailById(int committeeId) {
//        return approvalCommitteeMemberRepository.findById(committeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("ApprovalCommittee ", "committeeId", committeeId));
//    }
//
//    @Override
//    public void deleteApprovalCommitteeById(int committeeId) {
//        ApprovalCommittee approvalCommittee = approvalCommitteeRepository.findById(committeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("ApprovalCommittee  ", "committeeId", committeeId));
//        List<ApprovalCommitteeMember> approvalCommitteeMembers = approvalCommitteeMemberRepository
//                .findApprovalCommitteeMemberByApprovalCommitteeId(committeeId);
//        approvalCommitteeMemberRepository.deleteAll(approvalCommitteeMembers);
//        approvalCommitteeRepository.deleteById(committeeId);
//
//    }
//    @Override
//    public Optional<ApprovalCommittee> updateApprovalCommitteeStatus(int committeeId, ApprovalCommitteeRequest approvalCommitteeRequest) {
//        Optional<ApprovalCommittee> result = approvalCommitteeRepository.findById(committeeId);
//        if (result.isPresent()) {
//            ApprovalCommittee approvalCommittee = result.get();
//            approvalCommittee.setStatus(approvalCommitteeRequest.isStatus());
//            approvalCommitteeRepository.save(approvalCommittee);
//        } else {
//            throw new ResourceNotFoundException("ApprovalCommittee", "committeeId", committeeId);
//        }
//
//        return result;
//    }
//
//
//
//}
