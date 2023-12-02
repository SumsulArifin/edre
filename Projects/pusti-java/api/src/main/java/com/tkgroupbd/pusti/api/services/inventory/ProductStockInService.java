package com.tkgroupbd.pusti.api.services.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.ProductStockIn;
import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.ProductStockInRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductStockInService {
    MessageResponse createCollectProduct(ProductStockInRequest request);

    public Optional<ProductStockIn> updateProduct(int id, ProductStockInRequest request);

    List<ProductStockIn> getAllByCreationDateDesc();

    public List<Object[]> findStockyDistributor_idWithDate(int distributor_id, LocalDate startDate, LocalDate endDate);

    public List<ProductStockIn> getByCreatedAt(String createdAt);

    public List<ProductStockIn> findByReceivedFrom(ReceivedFrom receivedFrom);

    public List<Object[]> findSKUByDistributor_id(int distributor_id);

    public List<Object[]> findSKUByDistributor_idWithDate(int distributor_id, LocalDate startDate, LocalDate endDate);
}
