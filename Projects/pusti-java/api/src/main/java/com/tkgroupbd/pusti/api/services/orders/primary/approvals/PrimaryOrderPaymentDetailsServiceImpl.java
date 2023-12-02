package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderPaymentDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderPaymentDetailsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals.PrimaryOrderPaymentDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrimaryOrderPaymentDetailsServiceImpl implements PrimaryOrderPaymentDetailsService {

    @Autowired
    PrimaryOrderPaymentDetailsRepository primaryOrderPaymentDetailsRepository;

    @Override
    public PrimaryOrderPaymentDetails savePrimaryOrderPaymentDetails(
            PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest) {
        PrimaryOrderPaymentDetails primaryOrderPaymentDetails = new PrimaryOrderPaymentDetails();
        primaryOrderPaymentDetails.setPaymentMode(primaryOrderPaymentDetailsRequest.getPaymentMode());
        primaryOrderPaymentDetails.setPaymentDate(primaryOrderPaymentDetailsRequest.getPaymentDate());
        primaryOrderPaymentDetails
                .setPlannedPayableAmount(primaryOrderPaymentDetailsRequest.getPlannedPayableAmount());
        primaryOrderPaymentDetails
                .setPaySlipAttachedName(primaryOrderPaymentDetailsRequest.getPaySlipAttachedName());
        primaryOrderPaymentDetails.setBank(primaryOrderPaymentDetailsRequest.getBank());
        return primaryOrderPaymentDetailsRepository.save(primaryOrderPaymentDetails);
    }

    @Override
    public MessageResponse updatePrimaryOrderPaymentDetails(int id,
            PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest) {
        return null;
    }

    @Override
    public Optional<PrimaryOrderPaymentDetails> updatePrimaryOrderPaymentDetailsStatus(int id,
            PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest) {
        return Optional.empty();
    }

    @Override
    public int getPrimaryOrderPaymentDetailsId(PrimaryOrderPaymentDetails primaryOrderPaymentDetails) {
        return 0;
    }
}
