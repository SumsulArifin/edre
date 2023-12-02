package com.tkgroupbd.pusti.api.services.orders.offers;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers.OfferDTO;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OfferService {
    public Offer saveOfferWithDetails(OfferDTO request);

    public Optional<Offer> updateOffer(int id, OfferDTO request);

    public List<Offer> getAllOffers();

    public Offer getOfferById(int id);

    public void deleteOfferById(int id);

}
