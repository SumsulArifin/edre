package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RegionalHeads")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegionalHead extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionalHeadId;
    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "divisionId")
    private DivisionalHead divisionalHead;
}
