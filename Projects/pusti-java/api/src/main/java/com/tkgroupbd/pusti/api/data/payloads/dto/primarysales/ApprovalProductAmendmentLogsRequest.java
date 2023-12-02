package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovals;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalProductAmendmentLogsRequest extends BaseEntity {
    private String productSku;
    private long previous_quantity;
    private long current_quantity;
    private PrimaryOrderApprovals primaryOrderApprovals;
}
