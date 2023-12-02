package com.tkgroupbd.pusti.api.services.orders.offers;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.OfferType;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers.OfferTypeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OfferTypeService {
    MessageResponse createOfferType(OfferTypeDTO request);

    public Optional<OfferType> updateOfferType(int id, OfferTypeDTO request);

    public void deleteOfferTypeById(int id);

    public Optional<OfferType> updateOfferTypeStatus(int id, OfferTypeDTO request);

    public List<OfferType> getAllOfferType();

    public OfferType findOfferTypeById(int id);

    Page<OfferType> findOfferTypeByPagination(int offset, int pageSize);

    Page<OfferType> findSortedOfferTypeByPagination(int offset, int pageSize, String field);

}
