package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrderApprovalActivitiesRepository;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrderPaymentDetailsRepository;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrderProductDetailsRepository;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrdersRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrimaryOrderServiceImpl implements PrimaryOrderService {

    @Autowired
    PrimaryOrdersRepository primaryOrdersRepository;
    @Autowired
    PrimaryOrderProductDetailsRepository primaryOrderProductDetailsRepository;
    @Autowired
    PrimaryOrderPaymentDetailsRepository primaryOrderPaymentDetailsRepository;
    @Autowired
    PrimaryOrderApprovalActivitiesRepository primaryOrderApprovalActivitiesRepository;

    @Override
    public PrimaryOrder savePrimaryOrder(PrimaryOrder primaryOrdersRequest) {
        PrimaryOrder primaryOrder = new PrimaryOrder();

        primaryOrder.setTitle(primaryOrdersRequest.getTitle());
        primaryOrder.setApprovalStatus(primaryOrdersRequest.getApprovalStatus());
        primaryOrder.setHasDeliveryApproval(primaryOrdersRequest.isHasDeliveryApproval());
        primaryOrder.setDeliveryApprovalApproved(false);
        primaryOrder.setDepot(primaryOrdersRequest.getDepot());
        primaryOrder.setDistributor(primaryOrdersRequest.getDistributor());
        primaryOrder.setUser(primaryOrdersRequest.getUser());
        primaryOrder.setBrowser(primaryOrdersRequest.getBrowser());
        primaryOrder.setStatus(primaryOrdersRequest.isStatus());
        primaryOrder.setCreatedAt(primaryOrdersRequest.getCreatedAt());
        primaryOrder.setDeletedAt(primaryOrdersRequest.getDeletedAt());
        primaryOrder.setCreatedBy(primaryOrdersRequest.getCreatedBy());
        primaryOrder.setIp(primaryOrdersRequest.getIp());

        // Save the product entity
        primaryOrdersRepository.save(primaryOrder);

        primaryOrder.setPrimaryOrderId(primaryOrder.getPrimaryOrderId());

        return primaryOrder;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public MessageResponse updatePrimaryOrder(int primaryOrderId, PrimaryOrderRequest primaryOrderRequest) {
        Optional<PrimaryOrder> result = primaryOrdersRepository.findById(primaryOrderId);

        if (result.isPresent()) {
            PrimaryOrder primaryOrder = result.get();
            primaryOrder.setTitle(primaryOrderRequest.getTitle());
            primaryOrder.setApprovalStatus(primaryOrderRequest.getApprovalStatus());
            primaryOrder.setHasDeliveryApproval(primaryOrderRequest.isHasDeliveryApproval());
            primaryOrder.setDeliveryApprovalApproved(primaryOrderRequest.isDeliveryApprovalApproved());
            primaryOrder.setDepot(primaryOrderRequest.getDepot());
            primaryOrder.setDistributor(primaryOrderRequest.getDistributor());
            primaryOrder.setUser(primaryOrderRequest.getUser());
            primaryOrder.setBrowser(primaryOrderRequest.getBrowser());
            primaryOrder.setStatus(primaryOrderRequest.isStatus());
            primaryOrder.setCreatedAt(primaryOrderRequest.getCreatedAt());
            primaryOrder.setDeletedAt(primaryOrderRequest.getDeletedAt());
            primaryOrder.setCreatedBy(primaryOrderRequest.getCreatedBy());
            primaryOrder.setIp(primaryOrderRequest.getIp());

            return new MessageResponse(Message.SUCCESS_CREATION);

        } else
            return new MessageResponse(Message.FAILED_CREATION);

    }

    @Override
    public Optional<PrimaryOrder> updatePrimaryOrderStatus(int primaryOrderId,
            PrimaryOrderRequest primaryOrdersRequest) {
        Optional<PrimaryOrder> result = primaryOrdersRepository.findById(primaryOrderId);
        if (result.isPresent()) {
            PrimaryOrder primaryOrders = result.get();
            primaryOrders.setStatus(primaryOrdersRequest.isStatus());
            primaryOrdersRepository.save(primaryOrders);
        } else {
            throw new ResourceNotFoundException("PrimaryOrder", "primaryOrderId", primaryOrderId);
        }

        return result;
    }

    @Override
    public int getPrimaryOrderId(PrimaryOrder primaryOrder) {
        return primaryOrder.getPrimaryOrderId();
    }

}
