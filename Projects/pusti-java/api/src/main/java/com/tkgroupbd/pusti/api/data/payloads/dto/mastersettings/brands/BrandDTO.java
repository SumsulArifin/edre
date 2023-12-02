package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.brands;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandDTO extends BaseEntity {
    @NotBlank(message = "  cannot be blank")
    @NotNull(message = "  cannot be null")
    @Size(min = 3, max = 50, message = "Brand name accept maximum 50 characters and minimum 3 characters.")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String brandName;
    @NotBlank(message = "BrandType: cannot be blank")
    @NotNull(message = " BrandType: cannot be null")
    private String brandType;
}
