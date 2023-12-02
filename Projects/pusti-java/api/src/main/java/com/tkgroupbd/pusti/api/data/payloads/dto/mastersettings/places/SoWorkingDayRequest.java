package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SoWorkingDayRequest extends BaseEntity {
    private boolean workOnSat;
    private boolean workOnSun;
    private boolean workOnMon;
    private boolean workOnTues;
    private boolean workOnWed;
    private boolean workOnThu;
    private boolean workOnFri;
    private AssignedSalesOfficer assignedSalesOfficer;
}
