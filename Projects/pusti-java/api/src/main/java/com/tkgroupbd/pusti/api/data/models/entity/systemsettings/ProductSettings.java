package com.tkgroupbd.pusti.api.data.models.entity.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryCriteria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productSettings")
public class ProductSettings extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deliveryInDays;
    @Enumerated(EnumType.STRING)
    private DeliveryCriteria deliveryCriteria;
    @ManyToOne
    @JoinColumn(name = "pId")
    private Product products;
}
