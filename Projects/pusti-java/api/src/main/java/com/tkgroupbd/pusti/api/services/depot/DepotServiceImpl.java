package com.tkgroupbd.pusti.api.services.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.DepotRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.depot.DepotRepository;
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
public class DepotServiceImpl implements DepotService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DepotRepository depotRepository;

    @Override
    public MessageResponse addDepot(DepotRequest depotRequest) {
        try {
            Depot newDepot = new Depot();
            newDepot.setDepotName(depotRequest.getDepotName());
            newDepot.setEmail(depotRequest.getEmail());
            newDepot.setPhone(depotRequest.getPhone().replace(" ","").trim());
            newDepot.setDepartment(depotRequest.getDepartment());
            newDepot.setStatus(depotRequest.isStatus());
            depotRepository.save(newDepot);
            return new MessageResponse(Message.SUCCESS_DEPOT_CREATION);
        }catch (Exception e ){
            return new MessageResponse(Message.FAILED_CREATION +e.getMessage());
        }

    }

    @Override
    public Optional<Depot> updateDepot(Integer depotId, DepotRequest depotRequest) {
        Optional<Depot> result = depotRepository.findById(depotId);
        if (result.isPresent()) {
            Depot newDepot = result.get();
            newDepot.setDepotName(depotRequest.getDepotName());
            newDepot.setEmail(depotRequest.getEmail());
            newDepot.setPhone(depotRequest.getPhone());
            newDepot.setStatus(depotRequest.isStatus());
            newDepot.setDepartment(depotRequest.getDepartment());
            depotRepository.save(newDepot);
        } else{
            throw new ResourceNotFoundException("Depot", "depotId", depotId);
        }
        return result;
    }


    @Override
    public Optional<Depot> statusChangeDepot(Integer depotId, DepotRequest depotRequest) {
        Optional<Depot> result = depotRepository.findById(depotId);
        if (result.isPresent()) {
            Depot newDepot = result.get();
            newDepot.setStatus(depotRequest.isStatus());
            depotRepository.save(newDepot);
        } else{
            throw new ResourceNotFoundException("Depot", "depotId", depotId);
        }
        return result;
    }


    @Override
    public void deleteDepot(Integer depotId) {
        depotRepository.deleteById(depotId);

    }

    @Override
    public Depot getDeportById(Integer depotId) {
        return depotRepository.findById(depotId)
                .orElseThrow(() -> new ResourceNotFoundException("Deport", "depotId", depotId));
    }

    @Override
    public List<Depot> getAllDepots() {
        return depotRepository.findAll();
    }

    @Override
    public List<Depot> findSortedDepotsByKey(String field) {
        return depotRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Depot> findDepotByPagination(int offset, int pageSize) {
        Page<Depot> depots = depotRepository.findAll(PageRequest.of(offset, pageSize));
        return depots;

    }

    @Override
    public Page<Depot> findSortedDepotByPagination(int offset, int pageSize, String field) {
        Page<Depot> depots = depotRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return depots;
    }

    @Override
    public List<Depot> findDepotByDepotName(String name) {
        List<Depot> depots = depotRepository.findByNameContaining(name);
        return depots;
    }
}
