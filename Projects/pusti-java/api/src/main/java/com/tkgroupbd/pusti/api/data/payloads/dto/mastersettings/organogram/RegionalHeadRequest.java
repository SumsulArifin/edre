package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.DivisionalHead;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RegionalHeadRequest extends BaseEntity {
    @NotBlank(message = " Invalid name:  name can not be blank.")
    @NotNull(message = " Invalid name: name  can not null.")
    @Size(min = 3, max = 30, message = " Invalid name size : minimum  3 characters and maximum 30 characters will acceptable.")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String name;
    private DivisionalHead divisionalHead;
}
