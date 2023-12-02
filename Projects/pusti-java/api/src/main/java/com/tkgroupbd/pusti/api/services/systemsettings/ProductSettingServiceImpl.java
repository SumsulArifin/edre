package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.ProductSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.ProductSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.ProductSettingsRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSettingServiceImpl implements ProductSettingService {
    @Autowired
    private ProductSettingsRepository repository;

    @Override
    public MessageResponse saveProductSetting(ProductSettingRequest productSettingRequest) {
        try{
            ProductSettings productSetting = new ProductSettings();

            productSetting.setDeliveryInDays(productSettingRequest.getDeliveryInDays());
            productSetting.setProducts(productSettingRequest.getProduct());
            productSetting.setDeliveryCriteria(productSettingRequest.getDeliveryCriteria());

            repository.save(productSetting);
            return new MessageResponse(Message.SUCCESS_PRODUCTSETTING_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }

    }

    @Override
    public Optional<ProductSettings> updateProductSetting(int id, ProductSettingRequest productSettingRequest) {

        Optional<ProductSettings> result = repository.findById(id);

        if (result.isPresent()) {
            ProductSettings productSetting = result.get();
            productSetting.setDeliveryInDays(productSettingRequest.getDeliveryInDays());
            productSetting.setProducts(productSettingRequest.getProduct());
            productSetting.setDeliveryCriteria(productSettingRequest.getDeliveryCriteria());

            repository.save(productSetting);
        } else {
            throw new ResourceNotFoundException("ProductSetting", "id", id);
        }

        return result;

    }

    @Override
    public void deleteProductSettingById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductSettings> updateProductSettingStatus(int id, ProductSettingRequest productSettingRequest) {
        Optional<ProductSettings> productSetting = repository.findById(id);

        if (productSetting.isEmpty()) {
            throw new ResourceNotFoundException("ProductSetting", "id", id);
        } else
            productSetting.get().setStatus(productSettingRequest.isStatus());

        repository.save(productSetting.get());
        return productSetting;
    }

    @Override
    public List<ProductSettings> getAllProductSetting() {
        return repository.findAll();
    }

    @Override
    public ProductSettings findProductSettingById(int id) {
        return repository.findById(id).get();
    }
}
