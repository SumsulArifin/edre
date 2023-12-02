package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryOrderRequest extends BaseEntity {

    private String title;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private boolean hasDeliveryApproval;
    private boolean isDeliveryApprovalApproved;
    private Distributor distributor;
    private Depot depot;
    private User user;
}
