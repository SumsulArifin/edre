package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

import com.tkgroupbd.pusti.api.data.models.enums.OrderType;

@Data
public class OrderApprovalSettingsRequest {
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private boolean isApprovalRequired;
    private String upDatedBy;
    private Date updatedAt;
}
