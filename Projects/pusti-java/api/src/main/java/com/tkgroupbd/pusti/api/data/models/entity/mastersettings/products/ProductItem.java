package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
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
@Table(name = "products_items")
public class ProductItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proDetailsId;
    private String sku;
    @Enumerated(EnumType.STRING)
    private UnitId sellableUnitId;
    private String erpId;
    private double weight;
    private double piecePerSale = 1;
    private int piecePerCartoon;
    private LocalDate openingDate;
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
