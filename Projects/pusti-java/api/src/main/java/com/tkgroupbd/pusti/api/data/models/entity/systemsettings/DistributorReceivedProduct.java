package com.tkgroupbd.pusti.api.data.models.entity.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
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
@Table(name = "distributor_received_products")
public class DistributorReceivedProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String receivedYearMonth;
    private int receivedQuantity;
    private double distributorPrice;
    private double receivedFree;
    private double receivedAdjusted;
    private double receivedFreeAdjusted;
    private double blockedStock;
    private String operationType;

    @ManyToOne
    @JoinColumn(name = "pId")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

}
