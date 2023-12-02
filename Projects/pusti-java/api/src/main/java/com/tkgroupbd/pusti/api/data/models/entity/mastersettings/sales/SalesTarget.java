package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.enums.AudienceType;
import com.tkgroupbd.pusti.api.data.models.enums.TargetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salesTarget")
public class SalesTarget extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private TargetType targetType;
    @Enumerated(EnumType.STRING)
    private AudienceType audienceType;
    private int audienceId;
    private String yearmonth;
    private String quantity;
    private String dp;
    private String tp;
    private String dealerId;
    private String tsoId;
    private String rsmId;
    private String asmId;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductItem productItem;

    @ManyToOne
    @JoinColumn(name = "assignedId")
    private AssignedSalesOfficer assignedSalesOfficer;
}
