package com.tkgroupbd.pusti.api.services.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.IssuePanelActivities;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.IssuePanelActivitiesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.inventory.IssuePanelActivitiesRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssuePanelActivitiesServicesImpl implements IssuePanelActivitiesServices {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private IssuePanelActivitiesRepository issuePanelActivitiesRepository;

    @Override
    public MessageResponse addIssuePanelActivities(IssuePanelActivitiesRequest issuePanelActivitiesRequest) {
        try {
            IssuePanelActivities issuePanelActivities = new IssuePanelActivities();
            issuePanelActivities.setDistributor(issuePanelActivitiesRequest.getDistributor());
            issuePanelActivities.setDeliveryStatus(issuePanelActivitiesRequest.getDeliveryStatus());
            issuePanelActivities.setDeliveryDate(issuePanelActivitiesRequest.getDeliveryDate());

            issuePanelActivitiesRepository.save(issuePanelActivities);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }
    }

    @Override
    public Optional<IssuePanelActivities> updateIssuePanelActivities(int issuePanelActivitiesId,
            IssuePanelActivitiesRequest issuePanelActivitiesRequest) {
        Optional<IssuePanelActivities> result = Optional
                .ofNullable(issuePanelActivitiesRepository.findById(issuePanelActivitiesId)
                        .orElseThrow(() -> new ResourceNotFoundException("IssuePanelActivities",
                                "issuePanelActivitiesId", issuePanelActivitiesId)));

        if (result.isPresent()) {
            IssuePanelActivities issuePanelActivities = result.get();
            issuePanelActivities.setDistributor(issuePanelActivitiesRequest.getDistributor());
            issuePanelActivities.setDeliveryStatus(issuePanelActivitiesRequest.getDeliveryStatus());
            issuePanelActivities.setDeliveryDate(issuePanelActivitiesRequest.getDeliveryDate());

            issuePanelActivitiesRepository.save(issuePanelActivities);
        } else {
            throw new ResourceNotFoundException("IssuePanelActivities", "issuePanelActivitiesId",
                    issuePanelActivitiesId);
        }
        return result;
    }

    @Override
    public void deleteIssuePanelActivities(int issuePanelActivitiesId) {
        issuePanelActivitiesRepository.deleteById(issuePanelActivitiesId);

    }

    @Override
    public IssuePanelActivities getIssuePanelActivitiesById(int issuePanelActivitiesId) {
        return issuePanelActivitiesRepository.findById(issuePanelActivitiesId).get();
    }

    @Override
    public List<IssuePanelActivities> getAllIssuePanelActivities() {
        return issuePanelActivitiesRepository.findAll();
    }
}
