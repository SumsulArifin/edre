package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.*;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends BaseEntity {
    private int productId;
    @NotBlank(message = "Invalid : name cannot be blank")
    @NotNull(message = "Invalid : name cannot be null")
    private String name;
    private String bengaliName;
    private String erpId;
    @NotBlank(message = "Invalid :  sku number cannot be blank")
    @NotNull(message = "Invalid : sku number cannot be null")
    private String skuNumber;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    private double distributionPrice;
    private double tradingPrice;
    private double weightPerUnit;
    private int sellingPackSize;
    private int sellingCartoonSize;
    private boolean isNewProduct;
    private boolean isOfferRunning;
    private boolean isDistributionGiftAvailable;
    private boolean isSMSActive;
    private boolean isInStock;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private boolean isActive;
    private Category category;
    private Brand brand;

}
