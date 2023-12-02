package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlternateChannelSettingsRequest extends BaseEntity {
    private String startDate;
    private String endDate;
    private String eligibleAreaIds;
    private String eligibleOutletIds;
    private Product products;

}
