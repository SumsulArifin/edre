package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OrderApprovalSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.OrderApprovalSettingsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderApprovalSettingsService {
    MessageResponse createOrderApprovalSettings(OrderApprovalSettingsRequest orderApprovalSettingsRequest);

    Optional<OrderApprovalSettings> updateOrderApprovalSettings(Integer id,
            OrderApprovalSettingsRequest orderApprovalSettingsRequest);

    OrderApprovalSettings getOrderApprovalSettingsById(Integer id);

    List<OrderApprovalSettings> getAllOrderApprovalSettings();

}
