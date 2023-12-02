package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesOrganizationRequest extends BaseEntity {

    @NotBlank(message = "Invalid name: Sales Organization name  cannot be blank")
    @NotNull(message = "Invalid name: Sales Organization name cannot be null")
    @Size(min = 3, max = 20, message = "National Name is max 20, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String salesOrgName;
}
