package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovals;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PrimaryOrderApprovalsRequest;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrimaryOrderApprovalsServices {

    public PrimaryOrderApprovals savePrimaryOrderApprovalsWithOther(
            PrimaryOrderApprovalsRequest primaryOrderApprovalsRequest);

    public List<PrimaryOrderApprovals> getAllPrimaryOrderApprovals();

    public PrimaryOrderApprovals getPrimaryOrderApprovalsById(long id);

}
