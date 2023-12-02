package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryRequest extends BaseEntity {
    @NotNull(message = "Category name cannot be null")
    @NotBlank(message = "Category name cannot be blank")
    @NoSpecialCharacterAccept
    @NoNumberFirstCharacter
    @Size(min = 3, max = 30, message =  "Character size error : name must be 3 to 30 characters.")
    private String name;
    private CategoryType categoryType;
    private String description;
}
