package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.StorageUnit;
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
@Table(name = "primaryOrderProductDetails")
public class PrimaryOrderProductDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PrimaryOrderProductDetailsId;
    private int productSkuNumber;

    @Enumerated(EnumType.STRING)
    private StorageUnit storageUnit;
    private int quantity;
    private double distributionPrice;
    private double grossAmount;
    private double netAmount;

    @ManyToOne
    @JoinColumn(name = "primaryOrderId")
    private PrimaryOrder primaryOrder;
}
