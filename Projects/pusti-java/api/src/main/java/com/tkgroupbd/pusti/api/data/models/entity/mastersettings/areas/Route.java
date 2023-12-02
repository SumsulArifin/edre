package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "routes")
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;
    @Column(length = 50, nullable = false)
    private String routeName;
    @Column(length = 50, nullable = false)
    private double contributionPercentage;
    @Column(length = 50, nullable = false)
    private double latitude;
    @Column(length = 50, nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "zoneId", nullable = false)
    private Zone zone;


    @ManyToOne
    @JoinColumn(name = "distributorId", nullable = true)
    private Distributor distributor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "route_routeSalesOfficer",
            joinColumns = {
                    @JoinColumn(name = "routeId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "salesOfficerId")
            }

    )
    private Set<RouteSalesOfficer> routeSalesOfficerList = new HashSet<>();







}
