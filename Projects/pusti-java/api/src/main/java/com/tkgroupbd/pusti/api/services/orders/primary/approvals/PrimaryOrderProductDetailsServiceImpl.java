package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderProductDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderProductDetailsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrderProductDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrimaryOrderProductDetailsServiceImpl implements PrimaryOrderProductDetailsService {

    @Autowired
    PrimaryOrderProductDetailsRepository primaryOrderProductDetailsRepository;

    @Override
    public PrimaryOrderProductDetails savePrimaryOrderProductDetails(
            PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest) {
        PrimaryOrderProductDetails primaryOrderProductDetails = new PrimaryOrderProductDetails();
        primaryOrderProductDetails.setStorageUnit(primaryOrderProductDetailsRequest.getStorageUnit());
        primaryOrderProductDetails.setQuantity(primaryOrderProductDetailsRequest.getQuantity());
        primaryOrderProductDetails.setDistributionPrice(primaryOrderProductDetailsRequest.getDistributionPrice());
        primaryOrderProductDetails.setGrossAmount(primaryOrderProductDetailsRequest.getGrossAmount());
        primaryOrderProductDetails.setNetAmount(primaryOrderProductDetailsRequest.getNetAmount());

        return primaryOrderProductDetailsRepository.save(primaryOrderProductDetails);
    }

    @Override
    public MessageResponse updatePrimaryOrderProductDetails(int id,
            PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest) {
        return null;
    }

    @Override
    public Optional<PrimaryOrderProductDetails> updatePrimaryOrderProductDetailsStatus(int id,
            PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest) {
        return Optional.empty();
    }

    @Override
    public int getPrimaryOrderProductDetailsId(PrimaryOrderProductDetails primaryOrderProductDetails) {
        return primaryOrderProductDetails.getPrimaryOrderProductDetailsId();
    }
}
