package com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.offers.OfferCategory;
import com.tkgroupbd.pusti.api.data.models.enums.offers.ProductOfferUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OfferDetailsDTO extends BaseEntity {
    private OfferCategory offerIn;
    private ProductOfferUnit offeredUnitId;
    private double fromQuantity = 0;
    private double toQuantity = 0;
    private String comboCriteria;
    private double offeredQuantity = 0;
    private double offeredTaka;
}
