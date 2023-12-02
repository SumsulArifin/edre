package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OrderApprovalSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.OrderApprovalSettingsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.OrderApprovalSettingsRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderApprovalSettingsServiceImpl implements OrderApprovalSettingsService {
    @Autowired
    OrderApprovalSettingsRepository orderApprovalSettingsRepository;

    @Override
    public MessageResponse createOrderApprovalSettings(OrderApprovalSettingsRequest orderApprovalSettingsRequest) {

        try {
            OrderApprovalSettings orderApprovalSettings = new OrderApprovalSettings();
            orderApprovalSettings.setOrderType(orderApprovalSettingsRequest.getOrderType());
            orderApprovalSettings.setApprovalRequired(orderApprovalSettingsRequest.isApprovalRequired());
            orderApprovalSettings.setUpdatedBy(orderApprovalSettingsRequest.getUpDatedBy());

            orderApprovalSettingsRepository.save(orderApprovalSettings);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }

    }

    @Override
    public Optional<OrderApprovalSettings> updateOrderApprovalSettings(Integer id,
            OrderApprovalSettingsRequest orderApprovalSettingsRequest) {
        Optional<OrderApprovalSettings> results = orderApprovalSettingsRepository.findById(id);
        if (results.isPresent()) {
            OrderApprovalSettings orderApprovalSettings = results.get();
            orderApprovalSettings.setOrderType(orderApprovalSettingsRequest.getOrderType());
            orderApprovalSettings.setApprovalRequired(orderApprovalSettingsRequest.isApprovalRequired());
            orderApprovalSettings.setUpdatedBy(orderApprovalSettingsRequest.getUpDatedBy());

            orderApprovalSettingsRepository.save(orderApprovalSettings);

        } else {
            throw new ResourceNotFoundException("OrderApprovalSettings", "id", id);
        }
        return results;

    }

    @Override
    public OrderApprovalSettings getOrderApprovalSettingsById(Integer id) {
        return orderApprovalSettingsRepository.findById(id).get();
    }

    @Override
    public List<OrderApprovalSettings> getAllOrderApprovalSettings() {
        return orderApprovalSettingsRepository.findAll();
    }
}
