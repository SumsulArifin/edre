package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovalProcess;

import lombok.Data;

@Data
public class ApprovePrimaryOrdersRequest {
    private PrimaryOrderApprovalProcess primaryOrderApproval;
    private String comments;
}
