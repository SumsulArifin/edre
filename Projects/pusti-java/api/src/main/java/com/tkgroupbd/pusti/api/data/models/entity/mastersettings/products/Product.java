package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(length = 30, nullable = false)
    private String name;
    private String bengaliName;
    private String erpId;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closingDate;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;
}
