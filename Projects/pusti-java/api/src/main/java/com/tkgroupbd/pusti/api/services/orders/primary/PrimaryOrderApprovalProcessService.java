package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PrimaryOrderApprovalProcessRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public interface PrimaryOrderApprovalProcessService {
    MessageResponse createPrimaryOrderApproval(PrimaryOrderApprovalProcessRequest request);
}
