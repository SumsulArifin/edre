package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;

@Data
@EqualsAndHashCode(callSuper = false)
public class ZoneRequest extends BaseEntity {
    @NotBlank(message = "Zone name cannot be  blank")
    @NotNull(message = "Zone name cannot be null")
    @Size(min = 3, max = 20, message = "Zone Name is max 50, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String zoneName;
    private Region region;
}
