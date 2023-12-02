package com.tkgroupbd.pusti.api.controllers.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.Discount;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.DiscountDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.DiscountRequest;
import com.tkgroupbd.pusti.api.services.orders.primary.DiscountServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Discount")
@RestController
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    private DiscountServiceImpl discountService;

    @PostMapping("/save-with-details")
    public Discount saveDiscountWithDetails(@RequestBody DiscountRequest request) {
        return discountService.saveDiscountWithDetails(request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Discount>> updateOfferType(
            @PathVariable Integer id,
            @RequestBody DiscountRequest request) {
        Optional<Discount> discount = discountService.updateDiscount(id, request);
        return new ResponseEntity<Optional<Discount>>(discount, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.getAllDiscounts();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOfferTypeById(@PathVariable("id") Integer id) {
        discountService.deleteDiscountById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public DiscountDetails getDiscountDetailsById(@PathVariable int id) {
        return discountService.getDiscountDetailById(id);
    }
}
