package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import java.util.List;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "approvalCommittee")
public class ApprovalCommittee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int committeeId;
    private String committeeName;

    @OneToMany(mappedBy = "approvalCommittee", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<ApprovalCommitteeMember> approvalCommitteeMemberList;
}
