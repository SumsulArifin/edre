package com.tkgroupbd.pusti.api.data.repositories.primarysales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.DiscountDetails;

import java.util.List;

public interface DiscountDetailsRepository extends JpaRepository<DiscountDetails, Integer> {
     @Query("select o from DiscountDetails o where o.discount.id=?1")
     public List<DiscountDetails> findDiscountDetailsByDiscountId(int id);
}
