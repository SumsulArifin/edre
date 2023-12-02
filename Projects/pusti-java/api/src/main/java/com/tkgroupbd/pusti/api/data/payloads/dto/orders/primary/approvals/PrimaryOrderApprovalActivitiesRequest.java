package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryOrderApprovalActivitiesRequest extends BaseEntity {

    private String actionComments;
    private PrimaryOrder primaryOrders;
    private User user;
}
