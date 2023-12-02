package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ProductDistributionSchedules;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.ProductDistributionSchedulesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductDistributionSchedulesService {

        MessageResponse createProductDistributionSchedules(
                        ProductDistributionSchedulesRequest productDistributionSchedulesRequest);

        Optional<ProductDistributionSchedules> updateProductDistributionSchedules(Integer id,
                        ProductDistributionSchedulesRequest productDistributionSchedulesRequest);

        ProductDistributionSchedules getProductDistributionSchedulesById(Integer id);

        List<ProductDistributionSchedules> getAllProductDistributionSchedules();

        public void deleteProductDistributionScheduleById(int id);

}
