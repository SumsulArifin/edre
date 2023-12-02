package com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OfferTypeDTO extends BaseEntity {
    private String name;
}
