package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import com.tkgroupbd.pusti.api.data.models.enums.AudienceType;
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
@Table(name = "OfferAudiences")
public class OfferAudience extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private AudienceType audienceType;
    private String audienceIds;

    @ManyToOne
    @JoinColumn(name = "offerId")
    private Offer offer;
}
