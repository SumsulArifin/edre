package com.tkgroupbd.pusti.api.data.payloads.dto.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class IssuePanelActivitiesRequest {
    private String deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private Distributor distributor;

}
