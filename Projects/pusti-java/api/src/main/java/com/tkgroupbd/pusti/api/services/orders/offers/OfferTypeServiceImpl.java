package com.tkgroupbd.pusti.api.services.orders.offers;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.OfferType;
import com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers.OfferTypeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.orders.offers.OfferTypeRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferTypeServiceImpl implements OfferTypeService {
    @Autowired
    OfferTypeRepository repository;

    @Override
    public MessageResponse createOfferType(OfferTypeDTO request) {
        OfferType offerType = new OfferType();

        offerType.setName(request.getName());
        offerType.setStatus(request.isStatus());
        offerType.setCreatedBy(request.getCreatedBy());
        offerType.setUpdatedBy(request.getUpdatedBy());
        offerType.setCreatedAt(request.getCreatedAt());
        offerType.setUpdatedAt(request.getUpdatedAt());
        offerType.setDeletedAt(request.getDeletedAt());

        repository.save(offerType);

        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<OfferType> updateOfferType(int id, OfferTypeDTO request) {
        Optional<OfferType> result = repository.findById(id);

        if (result.isPresent()) {
            OfferType offerType = result.get();

            offerType.setName(request.getName());
            offerType.setStatus(request.isStatus());
            offerType.setCreatedBy(request.getCreatedBy());
            offerType.setUpdatedBy(request.getUpdatedBy());
            offerType.setCreatedAt(request.getCreatedAt());
            offerType.setUpdatedAt(request.getUpdatedAt());
            offerType.setDeletedAt(request.getDeletedAt());

            repository.save(offerType);

        } else {
            throw new ResourceNotFoundException("OfferType", "id", id);
        }

        return result;
    }

    @Override
    public void deleteOfferTypeById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<OfferType> updateOfferTypeStatus(int id, OfferTypeDTO request) {
        Optional<OfferType> result = repository.findById(id);

        if (result.isPresent()) {
            OfferType offerType = result.get();
            offerType.setStatus(request.isStatus());
            repository.save(offerType);

        } else {
            throw new ResourceNotFoundException("OfferType", "id", id);
        }

        return result;
    }

    @Override
    public List<OfferType> getAllOfferType() {
        return repository.findAll();
    }

    @Override
    public OfferType findOfferTypeById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OfferType", "id", id));
    }

    @Override
    public Page<OfferType> findOfferTypeByPagination(int offset, int pageSize) {
        Page<OfferType> offerTypes = repository.findAll(PageRequest.of(offset, pageSize));
        return offerTypes;
    }

    @Override
    public Page<OfferType> findSortedOfferTypeByPagination(int offset, int pageSize, String field) {
        Page<OfferType> offerTypes = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return offerTypes;
    }
}
