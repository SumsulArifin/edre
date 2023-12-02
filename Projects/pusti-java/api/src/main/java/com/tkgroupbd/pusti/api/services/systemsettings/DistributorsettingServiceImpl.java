package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.Distributorsetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorsettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.DistributorsettingRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributorsettingServiceImpl implements DistributorsettingService {

    @Autowired
    private DistributorsettingRepository repository;

    @Override
    public MessageResponse saveDistributorsetting(DistributorsettingRequest distributorsettingRequest) {
        Distributorsetting distributorsetting = new Distributorsetting();

        distributorsetting.setAssignedSkus(distributorsettingRequest.getAssignedSkus());
        distributorsetting.setMinimumDeliveryDays(distributorsettingRequest.getMinimumDeliveryDays());
        distributorsetting.setStatus(distributorsettingRequest.isStatus());
        distributorsetting.setCreatedAt(distributorsettingRequest.getCreatedAt());
        distributorsetting.setUpdatedAt(distributorsettingRequest.getUpdatedAt());
        distributorsetting.setDeletedAt(distributorsettingRequest.getDeletedAt());
        distributorsetting.setBrowser(distributorsettingRequest.getBrowser());
        distributorsetting.setIp(distributorsettingRequest.getIp());

        distributorsetting.setDistributor(distributorsettingRequest.getDistributor());

        repository.save(distributorsetting);
        return new MessageResponse(Message.SUCCESS_DISTRIBUTORSETTING_CREATION);
    }

    @Override
    public Optional<Distributorsetting> updateDistributorsetting(int id,
            DistributorsettingRequest distributorsettingRequest) {
        Optional<Distributorsetting> distributorsetting = repository.findById(id);

        if (distributorsetting.isEmpty()) {
            throw new ResourceNotFoundException("Distributorsetting", "id", id);
        } else
            distributorsetting.get().setAssignedSkus(distributorsettingRequest.getAssignedSkus());
        distributorsetting.get().setMinimumDeliveryDays(distributorsettingRequest.getMinimumDeliveryDays());
        distributorsetting.get().setStatus(distributorsettingRequest.isStatus());
        distributorsetting.get().setCreatedAt(distributorsettingRequest.getCreatedAt());
        distributorsetting.get().setUpdatedAt(distributorsettingRequest.getUpdatedAt());
        distributorsetting.get().setDeletedAt(distributorsettingRequest.getDeletedAt());
        distributorsetting.get().setBrowser(distributorsettingRequest.getBrowser());
        distributorsetting.get().setIp(distributorsettingRequest.getIp());

        distributorsetting.get().setDistributor(distributorsettingRequest.getDistributor());

        repository.save(distributorsetting.get());
        return distributorsetting;
    }

    @Override
    public void deleteDistributorsettingById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Distributorsetting> updateDistributorsettingStatus(int id,
            DistributorsettingRequest distributorsettingRequest) {
        Optional<Distributorsetting> distributorsetting = repository.findById(id);

        if (distributorsetting.isEmpty()) {
            throw new ResourceNotFoundException("Distributorsetting", "id", id);
        } else

            distributorsetting.get().setStatus(distributorsettingRequest.isStatus());

        repository.save(distributorsetting.get());
        return distributorsetting;
    }

    @Override
    public List<Distributorsetting> getAllDistributorsetting() {
        return repository.findAll();
    }

    @Override
    public Distributorsetting findDistributorsettingById(int id) {
        return repository.findById(id).get();
    }
}
