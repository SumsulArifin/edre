package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ZonalHeads")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ZonalHead extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zonalHeadId;
    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private RegionalHead regionalHead;
}
