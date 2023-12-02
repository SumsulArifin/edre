package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPriceRequest extends BaseEntity {
    private int proPriceId;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String sku;
    private double tradePrice;
    private double distributionPrice;
}
