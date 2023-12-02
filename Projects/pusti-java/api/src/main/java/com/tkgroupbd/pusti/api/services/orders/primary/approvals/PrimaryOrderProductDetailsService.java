package com.tkgroupbd.pusti.api.services.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderProductDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals.PrimaryOrderProductDetailsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PrimaryOrderProductDetailsService {
        PrimaryOrderProductDetails savePrimaryOrderProductDetails(
                        PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest);

        public MessageResponse updatePrimaryOrderProductDetails(int id,
                        PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest);

        public Optional<PrimaryOrderProductDetails> updatePrimaryOrderProductDetailsStatus(int id,
                        PrimaryOrderProductDetailsRequest primaryOrderProductDetailsRequest);

        public int getPrimaryOrderProductDetailsId(PrimaryOrderProductDetails primaryOrderProductDetails);
}
