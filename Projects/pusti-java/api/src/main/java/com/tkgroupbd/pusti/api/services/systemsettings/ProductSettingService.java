package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.ProductSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.ProductSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductSettingService {
    MessageResponse saveProductSetting(ProductSettingRequest productSettingRequest);

    public Optional<ProductSettings> updateProductSetting(int id, ProductSettingRequest productSettingRequest);

    public void deleteProductSettingById(int id);

    public Optional<ProductSettings> updateProductSettingStatus(int id, ProductSettingRequest productSettingRequest);

    public List<ProductSettings> getAllProductSetting();

    public ProductSettings findProductSettingById(int id);

}
