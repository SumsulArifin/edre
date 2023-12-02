package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RouteSalesOfficerRequest extends BaseEntity {
    private int visitingFrequency;
    private String preferredDays;
    private boolean isAddPermit;
    private boolean isEditPermit;
    private Route route;
    private Employee employee;
}
