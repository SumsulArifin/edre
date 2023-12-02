package com.tkgroupbd.pusti.api.controllers.orders.offers;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.OfferType;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers.OfferTypeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.orders.offers.OfferTypeServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Offer Type")
@RestController
@RequestMapping("/offerType")
public class OfferTypeController {
    @Autowired
    private OfferTypeServiceImpl service;

    // create Order Approval Info
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createOfferType(
            @RequestBody OfferTypeDTO request) {
        MessageResponse offerType = service.createOfferType(request);
        return new ResponseEntity<>(offerType, HttpStatus.CREATED);
    }

    // retrieve all
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<OfferType>> getAllOfferType() {
        List<OfferType> offerTypes = service.getAllOfferType();
        return new ResponseEntity<>(offerTypes, HttpStatus.OK);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<OfferType>> updateOfferType(
            @PathVariable Integer id,
            @RequestBody OfferTypeDTO request) {
        Optional<OfferType> offerType = service.updateOfferType(id, request);
        return new ResponseEntity<Optional<OfferType>>(offerType, HttpStatus.OK);
    }

    // Status Change API
    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<OfferType>> updateOfferTypeStatus(
            @PathVariable Integer id,
            @RequestBody OfferTypeDTO request) {
        Optional<OfferType> offerType = service.updateOfferTypeStatus(id, request);
        return new ResponseEntity<Optional<OfferType>>(offerType, HttpStatus.OK);
    }

    // Delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOfferTypeById(@PathVariable("id") Integer id) {
        service.deleteOfferTypeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<OfferType> findOfferTypeById(
            @PathVariable("id") Integer id) {
        OfferType offerType = service.findOfferTypeById(id);
        return new ResponseEntity<>(offerType, HttpStatus.OK);
    }

    @GetMapping("/getPaginated/{offset}/{pageSize}")
    private ApiResponse<Page<OfferType>> findOfferTypeByPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<OfferType> offerTypes = service.findOfferTypeByPagination(offset,
                pageSize);
        return new ApiResponse(offerTypes.getSize(), offerTypes);
    }

    @GetMapping("/getSortedPaginated/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<OfferType>> findSortedOfferTypeByPagination(
            @PathVariable int offset,
            @PathVariable int pageSize, @PathVariable String field) {
        Page<OfferType> offerTypes = service
                .findSortedOfferTypeByPagination(offset, pageSize, field);
        return new ApiResponse(offerTypes.getSize(), offerTypes);
    }
}
