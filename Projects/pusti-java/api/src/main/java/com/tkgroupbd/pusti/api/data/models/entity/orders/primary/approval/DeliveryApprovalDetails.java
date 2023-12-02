package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "delivery_approval_details")
public class DeliveryApprovalDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalDetailsId;
    private String title;
    private Integer primaryOrderId;
    private String productSku;
    private String unitId;
    private Integer requestedAmount;

    @ManyToOne
    @JoinColumn(name = "deliveryApprovalId")
    private DeliveryApproval deliveryApproval;
}