package com.tkgroupbd.pusti.api.data.repositories.orders.offers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
