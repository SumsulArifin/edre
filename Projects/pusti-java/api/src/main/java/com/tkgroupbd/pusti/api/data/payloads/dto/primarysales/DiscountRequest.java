package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class DiscountRequest extends BaseEntity {
    private String name;
    private String discountType;
    private String startDate;
    private String endDate;

    private List<DiscountDetailsRequest> discountDetails;
}
