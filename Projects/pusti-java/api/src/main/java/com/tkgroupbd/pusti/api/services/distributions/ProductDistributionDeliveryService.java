package com.tkgroupbd.pusti.api.services.distributions;

import com.tkgroupbd.pusti.api.data.models.entity.distributions.ProductDistributionDelivery;
import com.tkgroupbd.pusti.api.data.payloads.dto.distributions.ProductDistributionDeliveryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductDistributionDeliveryService {
    public MessageResponse createDistributionDelivery(ProductDistributionDeliveryRequest request);

    public Optional<ProductDistributionDelivery> updateProductDistributionDelivery(int id,
            ProductDistributionDeliveryRequest request);

    public Optional<ProductDistributionDelivery> statusChangeProductDistributionDelivery(int id,
            ProductDistributionDeliveryRequest request);

}