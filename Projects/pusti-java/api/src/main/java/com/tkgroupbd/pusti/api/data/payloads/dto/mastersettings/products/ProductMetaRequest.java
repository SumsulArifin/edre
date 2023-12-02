package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductMetaRequest extends BaseEntity {
    private int proMetaId;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String key;
    private String content;
    private String imageUrl;
}
