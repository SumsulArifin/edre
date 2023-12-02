package com.tkgroupbd.pusti.api.data.models.entity.factory;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

import com.tkgroupbd.pusti.api.data.models.entity.depot.AssignDepot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factories")
public class Factory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factoryId;
    @Column(length = 50, nullable = false)
    private String factoryName;
    @Column(length = 255, nullable = false)
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "factory_assign_depot",
            joinColumns = {
                    @JoinColumn(name = "factoryId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "assignDepotId")
            }
    )
    private Set<AssignDepot> assignDepots = new HashSet<>();

}
