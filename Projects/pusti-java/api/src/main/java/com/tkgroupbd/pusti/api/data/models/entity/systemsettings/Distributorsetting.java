package com.tkgroupbd.pusti.api.data.models.entity.systemsettings;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
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
@Table(name = "distributorsettings")
public class Distributorsetting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String assignedSkus;
    private Integer minimumDeliveryDays;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
}
