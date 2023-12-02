package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.DistributorRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DistributorRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistributorRepository distributorRepository;

    @Override
    public MessageResponse saveDistributor(DistributorRequest distributorRequest) {
        try {
            Distributor distributor = new Distributor();

            distributor.setName(distributorRequest.getName());
            distributor.setErpId(distributorRequest.getErpId());
            distributor.setProprietorName(distributorRequest.getProprietorName());
            distributor.setProprietorDob(distributorRequest.getProprietorDob());
            distributor.setMobile(distributorRequest.getMobile());
            distributor.setAddress(distributorRequest.getAddress());
            distributor.setHasPc(distributorRequest.isHasPc());
            distributor.setHasPrinter(distributorRequest.isHasPrinter());
            distributor.setHasLiveApp(distributorRequest.isHasLiveApp());
            distributor.setHasDirectSale(distributorRequest.isHasDirectSale());
            distributor.setOpeningDate(distributorRequest.getOpeningDate());
            distributor.setEmergencyContactName(distributorRequest.getEmergencyContactName());
            distributor.setEmergencyContactNumber(distributorRequest.getEmergencyContactNumber());
            distributor.setEmergencyContactRelation(distributorRequest.getEmergencyContactRelation());
            distributor.setStatus(distributorRequest.isStatus());
            distributor.setDepot(distributorRequest.getDepot());
            distributor.setZone(distributorRequest.getZone());
            distributor.setUpazila(distributorRequest.getUpazila());

            distributorRepository.save(distributor);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage() + e.getCause());
        }

    }

    @Override
    public Optional<Distributor> updateDistributor(int distributorId, DistributorRequest distributorRequest) {
        Optional<Distributor> result = distributorRepository.findById(distributorId);
        if (result.isPresent()) {
            Distributor distributor = result.get();
            distributor.setDepot(distributorRequest.getDepot());
            distributor.setName(distributorRequest.getName());
            distributor.setErpId(distributorRequest.getErpId());
            distributor.setProprietorName(distributorRequest.getProprietorName());
            distributor.setProprietorDob(distributorRequest.getProprietorDob());
            distributor.setMobile(distributorRequest.getMobile());
            distributor.setAddress(distributorRequest.getAddress());
            distributor.setHasPc(distributorRequest.isHasPc());
            distributor.setHasPrinter(distributorRequest.isHasPrinter());
            distributor.setHasLiveApp(distributorRequest.isHasLiveApp());
            distributor.setHasDirectSale(distributorRequest.isHasDirectSale());
            distributor.setOpeningDate(distributorRequest.getOpeningDate());
            distributor.setEmergencyContactName(distributorRequest.getEmergencyContactName());
            distributor.setEmergencyContactNumber(distributorRequest.getEmergencyContactNumber());
            distributor.setEmergencyContactRelation(distributorRequest.getEmergencyContactRelation());
            distributor.setStatus(distributorRequest.isStatus());
            distributor.setDepot(distributorRequest.getDepot());
            distributor.setZone(distributorRequest.getZone());
            distributor.setUpazila(distributorRequest.getUpazila());
            distributorRepository.save(distributor);
        } else {
            throw new ResourceNotFoundException("Distributor", "distributorId", distributorId);
        }
        return result;
    }

    @Override
    public void deleteDistributor(int distributorId) {
        distributorRepository.deleteById(distributorId);
    }

    @Override
    public Optional<Distributor> updateDistributorStatus(int distributorId, DistributorRequest distributorsRequest) {
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isEmpty()) {
            throw new ResourceNotFoundException("Distributor", "distributorId", distributorId);
        } else {
            distributor.get().setStatus(distributorsRequest.isStatus());
            distributorRepository.save(distributor.get());
            return distributor;
        }
    }

    @Override
    public List<Distributor> findSortedDistributorByKey(String field) {
        return distributorRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public List<Distributor> getAllDistributors() {
        return distributorRepository.findAll();
    }

    @Override
    public Distributor findDistributorById(int distributorId) {
        return distributorRepository.findById(distributorId).get();
    }

    public List<Distributor> getDistributorByDistributorName(String distributorName) {
        List<Distributor> distributorList = distributorRepository
                .findDistributorByDistributorNameContainingIgnoreCase(distributorName.toUpperCase().trim());
        return distributorList;
    }

    public List<Distributor> getDistributorByZoneName(String distributorName) {
        List<Distributor> distributorList = distributorRepository
                .findDistributorByZoneNameContainingIgnoreCase(distributorName.toUpperCase().trim());
        Collections.sort(distributorList, Comparator.comparing(Distributor::getName));
        return distributorList;
    }

    @Override
    public List<Distributor> getDistributorsByUpazilaId(int upazilaId) {
        List<Distributor> distributors = distributorRepository.findDistributorByUpazilaUpazilaId(upazilaId);
        return distributors;
    }

    @Override
    public Page<Distributor> findDistributorsByPagination(int offset, int pageSize) {
        Page<Distributor> distributors = distributorRepository.findAll(PageRequest.of(offset, pageSize));
        return distributors;
    }
}
