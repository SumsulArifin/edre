package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.ProductSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.ProductSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.ProductSettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Product Settings")
@RestController
@RequestMapping("/productSetting")
public class ProductSettingController {
    @Autowired
    private ProductSettingService productSettingService;

    @PostMapping("/addProductSetting")
    public ResponseEntity<MessageResponse> productSettingSave(@RequestBody ProductSettingRequest request) {
        MessageResponse newProductSetting = productSettingService.saveProductSetting(request);
        return new ResponseEntity<>(newProductSetting, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProductSettings")
    public ResponseEntity<List<ProductSettings>> getAllProductSetting() {
        List<ProductSettings> productSetting = productSettingService.getAllProductSetting();
        return new ResponseEntity<>(productSetting, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProductSettingById/{productSettingId}")
    public ResponseEntity<?> deleteProductSetting(@PathVariable("productSettingId") Integer productSettingId) {
        productSettingService.deleteProductSettingById(productSettingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProductSettingById/{productSettingId}")
    public ResponseEntity<ProductSettings> getProductSettingById(
            @PathVariable("productSettingId") Integer productSettingId) {
        ProductSettings productSetting = productSettingService.findProductSettingById(productSettingId);
        return new ResponseEntity<>(productSetting, HttpStatus.OK);
    }

    @PutMapping("/updateProductSetting/{productSettingId}")
    public ResponseEntity<Optional<ProductSettings>> updateProductSetting(
            @PathVariable("productSettingId") Integer productSettingId, @RequestBody ProductSettingRequest request) {
        Optional<ProductSettings> productSetting = productSettingService.updateProductSetting(productSettingId, request);
        return new ResponseEntity<Optional<ProductSettings>>(productSetting, HttpStatus.OK);
    }

    @PutMapping("/changeProductSettingStatus/{productSettingId}")
    public ResponseEntity<Optional<ProductSettings>> updateProductSettingStatus(
            @PathVariable("productSettingId") Integer productSettingId, @RequestBody ProductSettingRequest request) {
        Optional<ProductSettings> productSetting = productSettingService.updateProductSettingStatus(productSettingId, request);
        return new ResponseEntity<Optional<ProductSettings>>(productSetting, HttpStatus.OK);
    }

}
