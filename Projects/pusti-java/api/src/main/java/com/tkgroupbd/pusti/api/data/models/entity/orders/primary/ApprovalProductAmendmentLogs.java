package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ApprovalProductAmendmentLogs")
public class ApprovalProductAmendmentLogs extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productSku;
    private long previous_quantity;
    private long current_quantity;

    @ManyToOne
    @JoinColumn(name = "primaryOrderId")
    private PrimaryOrderApprovals primaryOrderApprovals;
}
