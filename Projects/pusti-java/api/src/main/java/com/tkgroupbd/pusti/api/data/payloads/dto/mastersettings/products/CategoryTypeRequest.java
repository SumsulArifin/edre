package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

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
public class CategoryTypeRequest extends BaseEntity {
    @NotNull(message = "Invalid Category type : Category type cannot be null")
    @NotBlank(message = "Invalid Category type : Category type cannot be blank")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    @Size(min = 3 , max = 30, message = "Size error: category type name minimum 3 characters and maximum size 30 characters.")
    private String name;
    private String description;
}
