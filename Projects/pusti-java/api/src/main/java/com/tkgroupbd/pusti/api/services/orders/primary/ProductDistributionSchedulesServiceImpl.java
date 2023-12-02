package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ProductDistributionSchedules;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.ProductDistributionSchedulesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.ProductDistributionSchedulesRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDistributionSchedulesServiceImpl implements ProductDistributionSchedulesService {

    @Autowired
    ProductDistributionSchedulesRepository productDistributionSchedulesRepository;

    @Override
    public MessageResponse createProductDistributionSchedules(
            ProductDistributionSchedulesRequest productDistributionSchedulesRequest) {
        try {
            ProductDistributionSchedules productDistributionSchedules = new ProductDistributionSchedules();
            productDistributionSchedules.setSKU(productDistributionSchedulesRequest.getSKU());
            productDistributionSchedules.setPlannedQuantity(productDistributionSchedulesRequest.getPlannedQuantity());
            productDistributionSchedules
                    .setDeliveredQuantity(productDistributionSchedulesRequest.getDeliveredQuantity());
            productDistributionSchedules.setComments(productDistributionSchedulesRequest.getComment());
            productDistributionSchedules.setPdsStatus(productDistributionSchedulesRequest.getPdsStatus());
            productDistributionSchedules.setDeliveryDate(productDistributionSchedulesRequest.getDeliveryDate());
            productDistributionSchedules.setCreatedBy(productDistributionSchedulesRequest.getCreatedBy());
            productDistributionSchedules.setUpdatedBy(productDistributionSchedulesRequest.getUpdatedBy());
            productDistributionSchedules.setPurchaseOrders(productDistributionSchedulesRequest.getPurchaseOrders());
            productDistributionSchedules.setCreatedAt(productDistributionSchedulesRequest.getCreatedAt());
            productDistributionSchedules.setUpdatedAt(productDistributionSchedulesRequest.getUpdatedAt());
            productDistributionSchedules.setDeletedAt(productDistributionSchedulesRequest.getDeletedAt());

            productDistributionSchedulesRepository.save(productDistributionSchedules);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }

    }

    @Override
    public Optional<ProductDistributionSchedules> updateProductDistributionSchedules(Integer id,
            ProductDistributionSchedulesRequest productDistributionSchedulesRequest) {
        Optional<ProductDistributionSchedules> results = productDistributionSchedulesRepository.findById(id);
        if (results.isPresent()) {
            ProductDistributionSchedules productDistributionSchedules = results.get();
            productDistributionSchedules.setSKU(productDistributionSchedulesRequest.getSKU());
            productDistributionSchedules.setPlannedQuantity(productDistributionSchedulesRequest.getPlannedQuantity());
            productDistributionSchedules
                    .setDeliveredQuantity(productDistributionSchedulesRequest.getDeliveredQuantity());
            productDistributionSchedules.setComments(productDistributionSchedulesRequest.getComment());
            productDistributionSchedules.setPdsStatus(productDistributionSchedulesRequest.getPdsStatus());
            productDistributionSchedules.setDeliveryDate(productDistributionSchedulesRequest.getDeliveryDate());
            productDistributionSchedules.setCreatedBy(productDistributionSchedulesRequest.getCreatedBy());
            productDistributionSchedules.setUpdatedBy(productDistributionSchedulesRequest.getUpdatedBy());
            productDistributionSchedules.setPurchaseOrders(productDistributionSchedulesRequest.getPurchaseOrders());
            productDistributionSchedules.setCreatedAt(productDistributionSchedulesRequest.getCreatedAt());
            productDistributionSchedules.setUpdatedAt(productDistributionSchedulesRequest.getUpdatedAt());
            productDistributionSchedules.setDeletedAt(productDistributionSchedulesRequest.getDeletedAt());
            productDistributionSchedulesRepository.save(productDistributionSchedules);
        } else {
            throw new ResourceNotFoundException("ProductDistributionSchedules", "id", id);
        }
        return results;
    }

    @Override
    public ProductDistributionSchedules getProductDistributionSchedulesById(Integer id) {
        return productDistributionSchedulesRepository.findById(id).get();
    }

    @Override
    public List<ProductDistributionSchedules> getAllProductDistributionSchedules() {
        return productDistributionSchedulesRepository.findAll();
    }

    @Override
    public void deleteProductDistributionScheduleById(int id) {
        productDistributionSchedulesRepository.deleteById(id);

    }
}
