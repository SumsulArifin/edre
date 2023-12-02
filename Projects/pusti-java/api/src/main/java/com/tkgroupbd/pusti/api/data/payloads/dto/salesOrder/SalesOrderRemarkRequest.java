package com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesOrderRemarkRequest extends BaseEntity {
    private String remark;
}
