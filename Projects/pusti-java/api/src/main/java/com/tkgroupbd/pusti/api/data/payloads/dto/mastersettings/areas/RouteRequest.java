package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import java.util.List;
import java.util.Set;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.RouteSalesOfficer;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RouteRequest extends BaseEntity {
    private int routeId;
    @NotBlank(message = " Route name cannot  be blank")
    @NotNull(message = " Route name cannot be null")
    @Size(min = 3, max = 50, message = "Acceptable size : Route Name is max 50, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String routeName;
    private int salesOfficerId;
    private double latitude;
    private double longitude;
    private Zone zone;
    private Distributor distributor;
    private double contributionPercentage;
    private Set<RouteSalesOfficer> routeSalesOfficerList;
}