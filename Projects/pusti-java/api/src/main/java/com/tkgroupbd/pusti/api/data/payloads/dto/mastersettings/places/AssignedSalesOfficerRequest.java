package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssignedSalesOfficerRequest extends BaseEntity {
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @NoSpecialCharacterAccept
    @NoNumberFirstCharacter
    @Size(min = 3, max = 30, message = "Invalid name : name will accept 3 to 30 characters")
    public String soName;
    private String prefireDay;
    private int scheduleOutlet;
    private int visitedOutlet;
    private int frequency;
    private Route route;
    private Distributor distributor;
    private SalesOfficer salesOfficer;
}
