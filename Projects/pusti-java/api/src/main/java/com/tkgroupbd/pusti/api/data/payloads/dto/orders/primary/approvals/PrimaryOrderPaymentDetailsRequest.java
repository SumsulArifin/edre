package com.tkgroupbd.pusti.api.data.payloads.dto.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrder;
import com.tkgroupbd.pusti.api.data.models.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryOrderPaymentDetailsRequest extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    private double plannedPayableAmount;
    private Date paymentDate;
    private String paySlipAttachedName;
    private Bank bank;
    private PrimaryOrder primaryOrders;

}
