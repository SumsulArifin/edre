package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
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
@Table(name = "PrimaryOrderApprovalProducts")
public class PrimaryOrderApprovalProducts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long productId;
    @Enumerated(EnumType.STRING)
    private UnitId unitId;
    private int quantity;
    private long pricePerUnit;
    private long superDistributorId;

    @ManyToOne
    @JoinColumn(name = "productSku")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "primaryOrderId")
    private PrimaryOrderApprovals primaryOrderApprovals;
}
