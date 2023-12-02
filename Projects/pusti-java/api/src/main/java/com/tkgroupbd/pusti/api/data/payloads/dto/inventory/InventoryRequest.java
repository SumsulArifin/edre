package com.tkgroupbd.pusti.api.data.payloads.dto.inventory;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InventoryRequest extends BaseEntity {
    private String name;
    private String address;
}
