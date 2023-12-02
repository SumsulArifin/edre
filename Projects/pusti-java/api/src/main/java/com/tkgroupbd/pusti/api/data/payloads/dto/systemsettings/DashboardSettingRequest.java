package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.Feature;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DashboardSettingRequest extends BaseEntity {

    private Feature feature;
    private int roleId;

}
