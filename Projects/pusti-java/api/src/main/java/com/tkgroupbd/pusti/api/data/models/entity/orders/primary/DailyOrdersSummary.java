package com.tkgroupbd.pusti.api.data.models.entity.orders.primary;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
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
@Table(name = "dailyOrdersSummary")
public class DailyOrdersSummary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numberOfSkus;
    private String numberOfCategories;
    private double ccp;
    private double lpc;
    private int totalMemo;

    // @ManyToOne
    // @JoinColumn(name = "assignedSoId")
    // private SalesOfficer salesOfficer;

}
