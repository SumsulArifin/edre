package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalCommitteeMemberDTO extends BaseEntity {
    private Integer id;
    private String sequenceNumber;
    private Employee employee;
}
