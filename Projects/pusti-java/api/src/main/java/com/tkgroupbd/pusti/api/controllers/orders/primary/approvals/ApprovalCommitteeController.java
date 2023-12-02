package com.tkgroupbd.pusti.api.controllers.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.ApprovalCommitteeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.orders.primary.approvals.ApprovalCommitteeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/approvalCommittee")
@Tag(name = "Approval Committee")
public class ApprovalCommitteeController {
        @Autowired
        private ApprovalCommitteeService approvalCommitteeService;

        @PostMapping("/saveApprovalCommittee")
        public ApprovalCommittee saveApprovalCommittee(
                        @RequestBody ApprovalCommitteeDTO approvalCommitteeDTO) {
                return approvalCommitteeService
                                .saveApprovalCommittee(approvalCommitteeDTO);
        }

        @PutMapping("/update/{committeeId}")
        public ResponseEntity<MessageResponse> updateApprovalCommittee(@PathVariable int committeeId,
                        @RequestBody ApprovalCommitteeDTO request) {
                MessageResponse approvalCommittee = approvalCommitteeService.updateApprovalCommittee(committeeId,
                                request);
                return new ResponseEntity<>(approvalCommittee, HttpStatus.OK);
        }

        @GetMapping("/getAll")
        @ResponseBody
        public ResponseEntity<List<ApprovalCommittee>> getAllApprovalCommittee() {
                List<ApprovalCommittee> approvalCommittees = approvalCommitteeService.getAllApprovalCommittee();
                return new ResponseEntity<>(approvalCommittees, HttpStatus.OK);
        }

        @GetMapping("/getApprovalCommitteeById/{committeeId}")
        public ApprovalCommittee getApprovalCommitteeById(@PathVariable int committeeId) {
                return approvalCommitteeService.getApprovalCommitteeDetailsById(committeeId);
        }

        @PutMapping("/statusChange/{committeeId}")
        public ResponseEntity<Optional<ApprovalCommittee>> changeApprovalCommitteeStatus(
                        @PathVariable Integer committeeId,
                        @RequestBody ApprovalCommitteeDTO approvalCommitteeRequest) {
                Optional<ApprovalCommittee> approvalCommittee = approvalCommitteeService.updateApprovalCommitteeStatus(
                                committeeId,
                                approvalCommitteeRequest);
                return new ResponseEntity<Optional<ApprovalCommittee>>(approvalCommittee,
                                HttpStatus.OK);
        }
}
