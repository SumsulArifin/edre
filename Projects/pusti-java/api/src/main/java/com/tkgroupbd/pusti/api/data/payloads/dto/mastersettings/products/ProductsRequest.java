package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductsRequest extends BaseEntity {
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String bengaliName;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String distributionPrice;
    private String tradePrice;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    private String eRPId;
    private String weight;
    private String piecePerSale;
    private String piecePerCTN;
    private String openingDate;
    private String imageName;
    private Category category;
}
