package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

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
@Table(name = "regions")
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;
    @Column(length = 50, nullable = false)
    private String regionName;
    @Column(length = 50, nullable = false)
    private int totalDistributor;


    @ManyToOne
    @JoinColumn(name = "divisionId", nullable = false)
    private Division division;

}
