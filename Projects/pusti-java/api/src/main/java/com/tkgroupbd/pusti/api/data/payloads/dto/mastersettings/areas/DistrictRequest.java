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

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequest {
    @NotBlank(message = " Invalid name: name can not be blank.")
    @NotNull(message = " Invalid name: name  can not null.")
    @Size(min = 3, max = 50, message = " Invalid name size : minimum  3 characters and maximum 50 characters will acceptable.")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String districtName;
}
