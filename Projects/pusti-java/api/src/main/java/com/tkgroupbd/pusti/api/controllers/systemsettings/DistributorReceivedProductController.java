package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributorReceivedProduct;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorReceivedProductRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.DistributorReceivedProductService;
import com.tkgroupbd.pusti.api.services.systemsettings.DistributorReceivedProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Distributor Received Product")
@RestController
@RequestMapping("/distributorReceivedProduct")
public class DistributorReceivedProductController {
    @Autowired
    private DistributorReceivedProductService distributorReceivedProductService;

    // create new Distributor Received Product Setting
    @PostMapping("/addReceivedProduct")
    public ResponseEntity<MessageResponse> createDistributorReceivedProduct(
            @RequestBody DistributorReceivedProductRequest request) {
        MessageResponse receivedProduct = distributorReceivedProductService.createDistributorReceivedProduct(request);
        return new ResponseEntity<>(receivedProduct, HttpStatus.CREATED);
    }

    // retrieve all Distributor Received Product Setting
    @GetMapping("/getAllReceivedProducts")
    @ResponseBody
    public ResponseEntity<List<DistributorReceivedProduct>> getAllDistributorReceivedProduct() {
        List<DistributorReceivedProduct> receivedProducts = distributorReceivedProductService.getAllDistributorReceivedProduct();
        return new ResponseEntity<>(receivedProducts, HttpStatus.OK);
    }

    // Update DistributorReceivedProduct
    @PutMapping("/updateReceivedProduct/{id}")
    public ResponseEntity<Optional<DistributorReceivedProduct>> updateDistributorReceivedProduct(
            @PathVariable Integer id,
            @RequestBody DistributorReceivedProductRequest request) {
        Optional<DistributorReceivedProduct> receivedProduct = distributorReceivedProductService.updateDistributorReceivedProduct(id, request);
        return new ResponseEntity<Optional<DistributorReceivedProduct>>(receivedProduct, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/changeReceivedProductStatus/{id}")
    public ResponseEntity<Optional<DistributorReceivedProduct>> distributorReceivedProductStatusChange(
            @PathVariable Integer id,
            @RequestBody DistributorReceivedProductRequest request) {
        Optional<DistributorReceivedProduct> receivedProduct = distributorReceivedProductService.distributorReceivedProductStatusChange(id,
                request);
        return new ResponseEntity<Optional<DistributorReceivedProduct>>(receivedProduct, HttpStatus.OK);
    }

    // Delete Distributor Received Product by id
    @DeleteMapping("/deleteReceivedProduct/{id}")
    public ResponseEntity<?> deleteDistributorReceivedProduct(@PathVariable("id") Integer id) {
        distributorReceivedProductService.deleteDistributorReceivedProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve Distributor Received Product by id
    @GetMapping("/getReceivedProductById/{id}")
    public ResponseEntity<DistributorReceivedProduct> getDistributorReceivedProductById(
            @PathVariable("id") Integer id) {
        DistributorReceivedProduct receivedProduct = distributorReceivedProductService.getDistributorReceivedProductById(id);
        return new ResponseEntity<>(receivedProduct, HttpStatus.OK);
    }

    @GetMapping("/getPaginated/{offset}/{pageSize}")
    private ApiResponse<Page<DistributorReceivedProduct>> findAltChannelSettingsByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<DistributorReceivedProduct> receivedProducts = distributorReceivedProductService.findDistributorReceivedProductByPagination(offset,
                pageSize);
        return new ApiResponse(receivedProducts.getSize(), receivedProducts);
    }

    @GetMapping("/getSortedPaginated/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<DistributorReceivedProduct>> findSortedAltChannelSettingsByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<DistributorReceivedProduct> receivedProducts = distributorReceivedProductService
                .findSortedDistributorReceivedProductByPagination(offset, pageSize, field);
        return new ApiResponse(receivedProducts.getSize(), receivedProducts);
    }

}
