package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.UpazilaRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.UpazilaRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UpazilaServiceImpl implements UpazilaService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    UpazilaRepository upazilaRepository;

    @Override
    public List<Upazila> getAllUpazilas() {
        return upazilaRepository.findAll();
    }

    @Override
    public Upazila findUpazilaById(int upazilaId) {
        Optional<Upazila> optionalUpazila = upazilaRepository.findById(upazilaId);
        if (optionalUpazila.isPresent()) {
            return optionalUpazila.get();
        }
        else {
            throw new EntityNotFoundException("Upazila with " +upazilaId+ " id not found");
        }
    }

    @Override
    public List<Upazila> getUpazilaByName(String upazilaName) {

        List<Upazila> upazila = upazilaRepository.findByUpazilaNameContaining(upazilaName);
        Collections.sort(upazila, Comparator.comparing(Upazila::getUpazilaName));

        return upazila;
    }

    @Override
    public Optional<Upazila> updateUpazila(Integer upazilaId, UpazilaRequest upazilaRequest) {
        Optional<Upazila> result = upazilaRepository.findById(upazilaId);

        if (result.isPresent()) {
            Upazila upazila = result.get();
            upazila.setUpazilaName(upazilaRequest.getUpazilaName());
            upazila.setDistrict(upazilaRequest.getDistrict());

            upazilaRepository.save(upazila);
        } else {
            throw new ResourceNotFoundException("Upazila", "upazilaId", upazilaId);
        }
        return result;
    }

    @Override
    public Optional<Upazila> updateUpazilaStatus(int upazilaId, UpazilaRequest upazilaRequest) {
        Optional<Upazila> upazila = upazilaRepository.findById(upazilaId);
        if (upazila.isEmpty()){
            throw new ResourceNotFoundException("Upazila", "upazilaId", upazilaId);
        }
        else {
            upazila.get().setStatus(upazilaRequest.isStatus());
            upazilaRepository.save(upazila.get());
        }
        return upazila;
    }

    @Override
    public MessageResponse addNewUpazila(UpazilaRequest upazilaRequest) {
        try {
            Upazila upazila = new Upazila();
            upazila.setUpazilaName(upazilaRequest.getUpazilaName());
            upazila.setDistrict(upazilaRequest.getDistrict());
            upazilaRepository.save(upazila);

            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public void deleteUpazila(Integer upazilaId) {
        upazilaRepository.deleteById(upazilaId);
    }

    @Override
    public Page<Upazila> findUpazilasByPagination(int offset, int pageSize) {
        Page<Upazila> upazilas = upazilaRepository.findAll(PageRequest.of(offset, pageSize));
        return upazilas;
    }

}
