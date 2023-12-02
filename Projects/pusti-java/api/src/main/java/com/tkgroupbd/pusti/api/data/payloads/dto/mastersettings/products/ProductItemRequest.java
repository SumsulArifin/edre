package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductItemRequest extends BaseEntity {
    private int proDetailsId;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String sku;
    @Enumerated(EnumType.STRING)
    private UnitId sellableUnitId;
    private String erpId;
    private double weight;
    private double piecePerSale = 1;
    private int piecePerCartoon;
    private LocalDate openingDate;
    private String imageName;
    private ProductPriceRequest productPriceRequest;

}
