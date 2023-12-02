package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegionRequest extends BaseEntity {
    @NotBlank(message = " Region name cannot be blank")
    @NotNull(message = " Region name cannot  be null")
    @Size(min = 3, max = 50, message = " Region Name is max 50, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String regionName;
    private Division division;
    private int totalDistributor;
}
