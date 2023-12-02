package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.models.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "primaryOrderPaymentDetails")
public class PrimaryOrderPaymentDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    private double plannedPayableAmount;
    private Date paymentDate;
    private String paySlipAttachedName;

    @ManyToOne
    @JoinColumn(name = "bankId")
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "primaryOrderId")
    private PrimaryOrder primaryOrder;

}
