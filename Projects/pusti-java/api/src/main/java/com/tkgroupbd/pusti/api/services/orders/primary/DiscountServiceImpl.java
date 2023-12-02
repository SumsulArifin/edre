package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.Discount;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.DiscountDetails;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.DiscountDetailsRequest;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.DiscountRequest;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.DiscountDetailsRepository;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.DiscountRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountDetailsRepository discountDetailsRepository;

    @Override
    public Discount saveDiscountWithDetails(DiscountRequest request) {
        // Convert DiscountDTO to Discount entity
        Discount discount = new Discount();
        discount.setName(request.getName());
        discount.setDiscountType(request.getDiscountType());
        discount.setStatus(request.isStatus());
        discount.setStartDate(request.getStartDate());
        discount.setEndDate(request.getEndDate());
        discount.setCreatedBy(request.getCreatedBy());
        discount.setUpdatedBy(request.getUpdatedBy());
        discount.setCreatedAt(request.getCreatedAt());
        discount.setUpdatedAt(request.getUpdatedAt());
        discount.setDeletedAt(request.getDeletedAt());

        // Save the Discount entity
        discount = discountRepository.save(discount);

        // Loop through each DiscountDetailDTO and convert to DiscountDetail entity
        for (DiscountDetailsRequest detailsRequest : request.getDiscountDetails()) {
            DiscountDetails discountDetail = new DiscountDetails();
            discountDetail.setDiscount(discount);
            discountDetail.setCreatedBy(detailsRequest.getCreatedBy());
            discountDetail.setUpdatedBy(detailsRequest.getUpdatedBy());
            discountDetail.setCreatedAt(detailsRequest.getCreatedAt());
            discountDetail.setUpdatedAt(detailsRequest.getUpdatedAt());
            discountDetail.setDeletedAt(detailsRequest.getDeletedAt());
            discountDetail.setFromQuantity(detailsRequest.getFromQuantity());
            discountDetail.setToQuantity(detailsRequest.getToQuantity());
            discountDetail.setComboCriteria(detailsRequest.getComboCriteria());
            discountDetail.setOfferedTaka(detailsRequest.getOfferedTaka());

            // Save the DiscountDetail entity
            discountDetailsRepository.save(discountDetail);
        }

        return discount;
    }

    @Override
    public Optional<Discount> updateDiscount(int id, DiscountRequest request) {
        Optional<Discount> result = discountRepository.findById(id);

        if (result.isPresent()) {
            Discount discount = result.get();

            discount.setName(request.getName());
            discount.setDiscountType(request.getDiscountType());
            discount.setStatus(request.isStatus());
            discount.setStartDate(request.getStartDate());
            discount.setEndDate(request.getEndDate());
            discount.setCreatedBy(request.getCreatedBy());
            discount.setUpdatedBy(request.getUpdatedBy());
            discount.setCreatedAt(request.getCreatedAt());
            discount.setUpdatedAt(request.getUpdatedAt());
            discount.setDeletedAt(request.getDeletedAt());

            discountRepository.save(discount);

        } else {
            throw new ResourceNotFoundException("Discount", "id", id);
        }

        return result;
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public DiscountDetails getDiscountDetailById(int id) {
        return discountDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount ", "id", id));
    }

    @Override
    public void deleteDiscountById(int id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount  ", "id", id));

        // Delete associated DiscountDetails before deleting the Discount
        List<DiscountDetails> discountDetails = discountDetailsRepository.findDiscountDetailsByDiscountId(id);
        discountDetailsRepository.deleteAll(discountDetails);

        discountRepository.delete(discount);
    }

}
