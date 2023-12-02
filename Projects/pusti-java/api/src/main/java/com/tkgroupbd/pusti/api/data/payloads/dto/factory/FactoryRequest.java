package com.tkgroupbd.pusti.api.data.payloads.dto.factory;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.AssignDepotRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class FactoryRequest extends BaseEntity {
    @NotBlank(message = "Factory name cannot be blank")
    @NotNull(message = "Factory name cannot be null")
    @Size(min = 3, max = 255, message = "Factory Name is max 255, min is 3")
    @NoSpecialCharacterAccept
    @NoNumberFirstCharacter
    private String factoryName;
    private String address;
    private Set<AssignDepotRequest> assignDepots = new HashSet<>();
}
