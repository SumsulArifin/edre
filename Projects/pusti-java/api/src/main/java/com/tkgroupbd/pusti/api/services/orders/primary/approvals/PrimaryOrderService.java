package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PrimaryOrderService {
    PrimaryOrder savePrimaryOrder(PrimaryOrder primaryOrdersRequest);

    public MessageResponse updatePrimaryOrder(int id, PrimaryOrderRequest primaryOrdersRequest);

    public Optional<PrimaryOrder> updatePrimaryOrderStatus(int id, PrimaryOrderRequest primaryOrdersRequest);

    public int getPrimaryOrderId(PrimaryOrder primaryOrder);

}
