package com.tkgroupbd.pusti.api.data.payloads.dto.orders.offers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.sql.Date;
import java.util.List;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OfferDetails;

@Data
@EqualsAndHashCode(callSuper = false)
public class OfferDTO extends BaseEntity {
    private String name;
    private String offerType;
    private boolean status;
    private Date startDate;
    private Date endDate;
    private List<OfferDetails> offerDetailsRequests;
}
