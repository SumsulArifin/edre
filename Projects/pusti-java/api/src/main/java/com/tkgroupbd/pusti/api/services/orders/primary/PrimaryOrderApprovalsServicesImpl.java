package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.*;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PrimaryOrderApprovalProductsRequest;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PrimaryOrderApprovalsRequest;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.ApprovalProductAmendmentLogsRepository;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.PrimaryOrderApprovalProductsRepository;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.PrimaryOrderApprovalsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimaryOrderApprovalsServicesImpl implements PrimaryOrderApprovalsServices {
    @Autowired
    PrimaryOrderApprovalsRepository primaryOrderApprovalsRepository;
    @Autowired
    PrimaryOrderApprovalProductsRepository primaryOrderApprovalProductsRepository;
    @Autowired
    ApprovalProductAmendmentLogsRepository approvalProductAmendmentLogsRepository;

    @Override
    public PrimaryOrderApprovals savePrimaryOrderApprovalsWithOther(
            PrimaryOrderApprovalsRequest primaryOrderApprovalsRequest) {
        // Convert DiscountDTO to ApprovalCommittee entity
        PrimaryOrderApprovals primaryOrderApprovals = new PrimaryOrderApprovals();
        primaryOrderApprovals.setApprovalName(primaryOrderApprovalsRequest.getApprovalName());
        primaryOrderApprovals.setOfferNote(primaryOrderApprovalsRequest.getOfferNote());
        primaryOrderApprovals.setCreatedBy(primaryOrderApprovalsRequest.getCreatedBy());
        primaryOrderApprovals.setUpdatedBy(primaryOrderApprovalsRequest.getUpdatedBy());
        primaryOrderApprovals.setCreatedAt(primaryOrderApprovalsRequest.getCreatedAt());
        primaryOrderApprovals.setUpdatedAt(primaryOrderApprovalsRequest.getUpdatedAt());
        primaryOrderApprovals.setApprovalCommittee(primaryOrderApprovalsRequest.getApprovalCommittee());
        primaryOrderApprovals.setApprovalStatus(primaryOrderApprovalsRequest.getApprovalStatus());
        primaryOrderApprovals.setApprovalType(primaryOrderApprovalsRequest.getApprovalType());
        primaryOrderApprovals.setDistributor(primaryOrderApprovalsRequest.getDistributor());
        // Save the ApprovalCommittee entity
        primaryOrderApprovals = primaryOrderApprovalsRepository.save(primaryOrderApprovals);
        // Loop through each DiscountDetailDTO and convert to DiscountDetail entity
        for (PrimaryOrderApprovalProductsRequest primaryOrderApprovalProductsRequest : primaryOrderApprovalsRequest
                .getPrimaryOrderApprovalProductsRequests()) {
            PrimaryOrderApprovalProducts primaryOrderApprovalProducts = new PrimaryOrderApprovalProducts();
            primaryOrderApprovalProducts.setPrimaryOrderApprovals(primaryOrderApprovals);
            primaryOrderApprovalProducts.setProductId(primaryOrderApprovalProductsRequest.getProductId());
            primaryOrderApprovalProducts.setUnitId(primaryOrderApprovalProductsRequest.getUnitId());
            primaryOrderApprovalProducts.setQuantity(primaryOrderApprovalProductsRequest.getQuantity());
            primaryOrderApprovalProducts.setPricePerUnit(primaryOrderApprovalProductsRequest.getPricePerUnit());
            primaryOrderApprovalProducts
                    .setSuperDistributorId(primaryOrderApprovalProductsRequest.getSuperDistributorId());
            primaryOrderApprovalProducts.setCreatedBy(primaryOrderApprovalProductsRequest.getCreatedBy());
            primaryOrderApprovalProducts.setUpdatedBy(primaryOrderApprovalProductsRequest.getUpdatedBy());
            primaryOrderApprovalProducts.setProducts(primaryOrderApprovalProductsRequest.getProducts());
            primaryOrderApprovalProducts
                    .setPrimaryOrderApprovals(primaryOrderApprovalProductsRequest.getPrimaryOrderApprovals());
            // Save the DiscountDetail entity
            primaryOrderApprovalProductsRepository.save(primaryOrderApprovalProducts);
        }
        for (ApprovalProductAmendmentLogs approvalProductAmendmentLogsRequest : primaryOrderApprovalsRequest
                .getApprovalProductAmendmentLogsRequests()) {
            ApprovalProductAmendmentLogs approvalProductAmendmentLogs = new ApprovalProductAmendmentLogs();
            approvalProductAmendmentLogs.setPrimaryOrderApprovals(primaryOrderApprovals);
            approvalProductAmendmentLogs.setProductSku(approvalProductAmendmentLogsRequest.getProductSku());
            approvalProductAmendmentLogs
                    .setPrevious_quantity(approvalProductAmendmentLogsRequest.getPrevious_quantity());
            approvalProductAmendmentLogs.setCurrent_quantity(approvalProductAmendmentLogsRequest.getCurrent_quantity());
            approvalProductAmendmentLogs.setCreatedBy(approvalProductAmendmentLogsRequest.getCreatedBy());
            approvalProductAmendmentLogs.setUpdatedBy(approvalProductAmendmentLogsRequest.getUpdatedBy());
            approvalProductAmendmentLogs
                    .setPrimaryOrderApprovals(approvalProductAmendmentLogsRequest.getPrimaryOrderApprovals());
            // Save the DiscountDetail entity
            approvalProductAmendmentLogsRepository.save(approvalProductAmendmentLogs);
        }
        return primaryOrderApprovals;
    }

    @Override
    public List<PrimaryOrderApprovals> getAllPrimaryOrderApprovals() {
        return primaryOrderApprovalsRepository.findAll();
    }

    @Override
    public PrimaryOrderApprovals getPrimaryOrderApprovalsById(long id) {
        return primaryOrderApprovalsRepository.findById(id).get();
    }
}
