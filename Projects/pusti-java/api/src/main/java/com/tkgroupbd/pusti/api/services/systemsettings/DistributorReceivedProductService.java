package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributorReceivedProduct;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorReceivedProductRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DistributorReceivedProductService {
    MessageResponse createDistributorReceivedProduct(DistributorReceivedProductRequest request);

    Optional<DistributorReceivedProduct> updateDistributorReceivedProduct(Integer id,
            DistributorReceivedProductRequest request);

    void deleteDistributorReceivedProduct(Integer id);

    DistributorReceivedProduct getDistributorReceivedProductById(Integer id);

    List<DistributorReceivedProduct> getAllDistributorReceivedProduct();

    Optional<DistributorReceivedProduct> distributorReceivedProductStatusChange(Integer id,
            DistributorReceivedProductRequest request);

    Page<DistributorReceivedProduct> findDistributorReceivedProductByPagination(int offset, int pageSize);

    Page<DistributorReceivedProduct> findSortedDistributorReceivedProductByPagination(int offset, int pageSize,
            String field);

}
