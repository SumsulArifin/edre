package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovePrimaryOrders;
import com.tkgroupbd.pusti.api.data.models.enums.OrderType;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PurchaseOrderItemsRequest;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrdersRequest extends BaseEntity {
    @NotBlank(message = "Invalid message : name can not be blank")
    @NotNull(message = "Invalid message : name can not be null")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    @Size(min = 3, max = 30, message = "size error message : minimum size 3 character and maximum size is 30 character")
    private String name;
    private String superDistributorId;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private String isApprovalRequired;
    private String approvalStatus;
    private Depot depot;
    private Distributor distributor;
    private ApprovePrimaryOrders approvePrimaryOrders;
    private List<PurchaseOrderItemsRequest> purchaseOrderItemsRequests;
}
