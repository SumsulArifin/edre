package com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.ApprovalCommittee;

@Repository
public interface ApprovalCommitteeRepository extends JpaRepository<ApprovalCommittee, Integer> {

    @Query("SELECT ac FROM ApprovalCommittee ac LEFT JOIN ac.approvalCommitteeMemberList where ac.Id=?1")
    ApprovalCommittee getApprovalCommitteeDetailsById(int committeeId);
}
