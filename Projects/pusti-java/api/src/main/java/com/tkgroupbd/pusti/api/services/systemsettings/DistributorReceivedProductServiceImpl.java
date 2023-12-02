package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributorReceivedProduct;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorReceivedProductRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.DistributorReceivedProductRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributorReceivedProductServiceImpl implements DistributorReceivedProductService {

    @Autowired
    private DistributorReceivedProductRepository repository;

    @Override
    public MessageResponse createDistributorReceivedProduct(DistributorReceivedProductRequest request) {
        DistributorReceivedProduct receivedProduct = new DistributorReceivedProduct();

        receivedProduct.setReceivedYearMonth(request.getReceivedYearMonth());
        receivedProduct.setReceivedQuantity(request.getReceivedQuantity());
        receivedProduct.setDistributorPrice(request.getDistributorPrice());
        receivedProduct.setReceivedFree(request.getReceivedFree());
        receivedProduct.setReceivedAdjusted(request.getReceivedAdjusted());
        receivedProduct.setReceivedFreeAdjusted(request.getReceivedFreeAdjusted());
        receivedProduct.setBlockedStock(request.getBlockedStock());
        receivedProduct.setOperationType(request.getOperationType());
        receivedProduct.setProducts(request.getProducts());
        receivedProduct.setDistributor(request.getDistributor());

        repository.save(receivedProduct);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<DistributorReceivedProduct> updateDistributorReceivedProduct(Integer id,
            DistributorReceivedProductRequest request) {
        Optional<DistributorReceivedProduct> result = repository.findById(id);

        if (result.isPresent()) {
            DistributorReceivedProduct receivedProduct = result.get();

            receivedProduct.setReceivedYearMonth(request.getReceivedYearMonth());
            receivedProduct.setReceivedQuantity(request.getReceivedQuantity());
            receivedProduct.setDistributorPrice(request.getDistributorPrice());
            receivedProduct.setReceivedFree(request.getReceivedFree());
            receivedProduct.setReceivedAdjusted(request.getReceivedAdjusted());
            receivedProduct.setReceivedFreeAdjusted(request.getReceivedFreeAdjusted());
            receivedProduct.setBlockedStock(request.getBlockedStock());
            receivedProduct.setOperationType(request.getOperationType());
            receivedProduct.setProducts(request.getProducts());
            receivedProduct.setDistributor(request.getDistributor());

            repository.save(receivedProduct);

        } else {
            throw new ResourceNotFoundException("DistributorReceivedProduct", "id", id);
        }

        return result;
    }

    @Override
    public void deleteDistributorReceivedProduct(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public DistributorReceivedProduct getDistributorReceivedProductById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DistributorReceivedProduct", "id", id));
    }

    @Override
    public List<DistributorReceivedProduct> getAllDistributorReceivedProduct() {
        return repository.findAll();
    }

    @Override
    public Optional<DistributorReceivedProduct> distributorReceivedProductStatusChange(Integer id,
            DistributorReceivedProductRequest request) {
        Optional<DistributorReceivedProduct> result = repository.findById(id);

        if (result.isPresent()) {
            DistributorReceivedProduct receivedProduct = result.get();
            receivedProduct.setStatus(request.isStatus());
            repository.save(receivedProduct);

        } else {
            throw new ResourceNotFoundException("DistributorReceivedProduct", "id", id);
        }

        return result;
    }

    @Override
    public Page<DistributorReceivedProduct> findDistributorReceivedProductByPagination(int offset, int pageSize) {
        Page<DistributorReceivedProduct> receivedProducts = repository.findAll(PageRequest.of(offset, pageSize));
        return receivedProducts;
    }

    @Override
    public Page<DistributorReceivedProduct> findSortedDistributorReceivedProductByPagination(int offset, int pageSize,
            String field) {
        Page<DistributorReceivedProduct> receivedProducts = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return receivedProducts;
    }
}
