package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.Discount;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.DiscountDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.DiscountRequest;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DiscountService {
    public Discount saveDiscountWithDetails(DiscountRequest request);

    public Optional<Discount> updateDiscount(int id, DiscountRequest request);

    public List<Discount> getAllDiscounts();

    public DiscountDetails getDiscountDetailById(int id);

    public void deleteDiscountById(int id);
}
