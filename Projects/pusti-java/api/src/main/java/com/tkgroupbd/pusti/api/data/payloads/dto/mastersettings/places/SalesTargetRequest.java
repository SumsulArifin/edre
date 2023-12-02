package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.enums.AudienceType;
import com.tkgroupbd.pusti.api.data.models.enums.TargetType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesTargetRequest extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TargetType targetType;
    @Enumerated(EnumType.STRING)
    private AudienceType audienceType;
    private int audienceId;
    private String yearmonth;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String quantity;
    private String dp;
    private String tp;
    private String dealerId;
    private String tsoId;
    private String rsmId;
    private String asmId;
    private Region region;
    private ProductItem productItem;
    private AssignedSalesOfficer assignedSalesOfficer;
}
