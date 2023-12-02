package com.tkgroupbd.pusti.api.controllers.orders.offers;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers.OfferDTO;
import com.tkgroupbd.pusti.api.services.orders.offers.OfferExcelDownload;
import com.tkgroupbd.pusti.api.services.orders.offers.OfferServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Offer")
@RestController
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    private OfferServiceImpl offerService;

    @PostMapping("/save-with-details")
    public Offer saveOfferWithDetails(@RequestBody OfferDTO request) {
        return offerService.saveOfferWithDetails(request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Offer>> updateOfferType(
            @PathVariable Integer id,
            @RequestBody OfferDTO request) {
        Optional<Offer> offer = offerService.updateOffer(id, request);
        return new ResponseEntity<Optional<Offer>>(offer, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOfferTypeById(@PathVariable("id") Integer id) {
        offerService.deleteOfferById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public Offer getDiscountDetailsById(@PathVariable int id) {
        return offerService.getOfferById(id);
    }

    @GetMapping("/downloadExcel")
    public void exportIntoExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Offer.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Offer> offers = offerService.getAllOffers();
        OfferExcelDownload excelDownload = new OfferExcelDownload(offers);
        excelDownload.generateExcelFile(response);
    }
}
