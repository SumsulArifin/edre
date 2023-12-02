package com.tkgroupbd.pusti.api.data.models.entity.factory;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.models.enums.DeliveryStatus;
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
@Table(name = "receivingEntry")
public class IssuePanelActivities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issuePanelActivitiesId;
    private String deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
}
