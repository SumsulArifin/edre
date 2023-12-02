package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import com.tkgroupbd.pusti.api.data.models.enums.offers.OfferCategory;
import com.tkgroupbd.pusti.api.data.models.enums.offers.ProductOfferUnit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offerDetails")
public class OfferDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private OfferCategory offerIn;
    @Enumerated(EnumType.STRING)
    private ProductOfferUnit offeredUnitId;
    private double fromQuantity = 0;
    private double toQuantity = 0;
    private String comboCriteria;
    private double offeredQuantity = 0;
    private double offeredTaka;

    @ManyToOne
    @JoinColumn(name = "offerProductId")
    private Offer offer;
}
