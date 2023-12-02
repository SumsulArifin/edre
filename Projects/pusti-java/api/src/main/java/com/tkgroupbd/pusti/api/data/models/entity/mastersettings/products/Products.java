//package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products;
//
//import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
//import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//@Entity
//@Data
//@Table(name = "products")
//@EqualsAndHashCode(callSuper = false)
//public class Products extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int pId;
//    private String name;
//    private String bengaliName;
//    private String distributionPrice;
//    private String tradePrice;
//    @Enumerated(EnumType.STRING)
//    private UnitId unitId;
//    private String eRPId;
//    private String weight;
//    private String piecePerSale;
//    private String piecePerCTN;
//    private String openingDate;
//    private String imageName;
//    private String productSku;
//
//    @ManyToOne
//    @JoinColumn(name = "categoryId")
//    private Category category;
//}
