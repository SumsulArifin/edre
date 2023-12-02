package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zones")
@ToString
public class Zone extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zoneId;
    @Column(length = 50, nullable = false)
    private String zoneName;

    @ManyToOne
    @JoinColumn(name = "regionId", nullable = false)
    private Region region;

}
