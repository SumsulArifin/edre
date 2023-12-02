package com.tkgroupbd.pusti.api.data.payloads.dto.depot;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import lombok.Data;

@Data
public class AssignDepotRequest extends BaseEntity {
    private Depot depot;
}
