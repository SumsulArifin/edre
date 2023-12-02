package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.National;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivisionRequest extends BaseEntity {
    @NotBlank(message = " Invalid name: name can not be blank.")
    @NotNull(message = " Invalid name: name  can not null.")
    @Size(min = 3, max = 50, message = " Invalid name size : minimum  3 characters and maximum 50 characters will acceptable.")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String divisionName;
    private National national;
}
