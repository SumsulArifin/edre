package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DistributionTeamSettingRequest extends BaseEntity {

    private String teamName;

}
