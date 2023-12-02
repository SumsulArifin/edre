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
@Table(name = "discountDetails")
public class DiscountDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double fromQuantity = 0;
    private double toQuantity = 0;
    private String comboCriteria;
    private double offeredTaka;

    @ManyToOne
    @JoinColumn(name = "offerProductId")
    private Discount discount;
}
