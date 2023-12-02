package com.tkgroupbd.pusti.api.services.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.IssuePanelActivities;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.IssuePanelActivitiesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IssuePanelActivitiesServices {
    MessageResponse addIssuePanelActivities(IssuePanelActivitiesRequest issuePanelActivitiesRequest);

    Optional<IssuePanelActivities> updateIssuePanelActivities(int issuePanelActivitiesId,
            IssuePanelActivitiesRequest issuePanelActivitiesRequest);

    void deleteIssuePanelActivities(int issuePanelActivitiesId);

    IssuePanelActivities getIssuePanelActivitiesById(int issuePanelActivitiesId);

    List<IssuePanelActivities> getAllIssuePanelActivities();
}
