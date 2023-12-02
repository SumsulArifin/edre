package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderPaymentDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderPaymentDetailsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PrimaryOrderPaymentDetailsService {
        PrimaryOrderPaymentDetails savePrimaryOrderPaymentDetails(
                        PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest);

        public MessageResponse updatePrimaryOrderPaymentDetails(int id,
                        PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest);

        public Optional<PrimaryOrderPaymentDetails> updatePrimaryOrderPaymentDetailsStatus(int id,
                        PrimaryOrderPaymentDetailsRequest primaryOrderPaymentDetailsRequest);

        public int getPrimaryOrderPaymentDetailsId(PrimaryOrderPaymentDetails primaryOrderPaymentDetails);
}
