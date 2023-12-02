package com.tkgroupbd.pusti.api.data.models.entity.orders.offers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.offers.OfferCategory;
import com.tkgroupbd.pusti.api.data.models.enums.offers.OfferLevel;
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
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offerId;
    private String offerName;
    private OfferCategory offerCategory;
    private boolean isSingleProductBasedOffer;
    private OfferLevel offerLevel;
    private String offerTarget; // Comma separated ids which could be nation wide, area, region, DB, Brand etc.

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<OfferOnProduct> offerOnProductList = new ArrayList<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<OfferedProduct> offerProductList = new ArrayList<>();
}
