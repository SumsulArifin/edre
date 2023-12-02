package com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import com.tkgroupbd.pusti.api.data.models.enums.AudienceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OfferAudienceDTO extends BaseEntity {

    private AudienceType audienceType;
    @Enumerated(EnumType.STRING)
    private String audienceIds;
    private Offer offer;

}
