package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RouteRetrievalRequest {
    private int routeId;
    @NotBlank(message = " Route name cannot  be blank")
    @NotNull(message = " Route name cannot be null")
    @Size(min = 3, max = 50, message = "Acceptable size : Route Name is max 50, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String routeName;
    private int salesOfficerId;
}
