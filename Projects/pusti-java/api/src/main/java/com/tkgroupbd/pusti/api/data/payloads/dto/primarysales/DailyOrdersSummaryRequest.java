package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DailyOrdersSummaryRequest extends BaseEntity {
    private String numberOfSkus;
    private String numberOfCategories;
    private double ccp;
    private double lpc;
    private int totalMemo;
    private SalesOfficer salesOfficer;
}
