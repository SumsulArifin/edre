package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DistributorsettingRequest extends BaseEntity {
    private String assignedSkus;
    private Integer minimumDeliveryDays;
    private Distributor distributor;
}
