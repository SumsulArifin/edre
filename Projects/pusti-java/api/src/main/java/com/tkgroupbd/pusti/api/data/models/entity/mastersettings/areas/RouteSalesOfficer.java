package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;

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
@Table(name = "route_sales_officers")
public class RouteSalesOfficer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private int visitingFrequency;
    private String preferredDays;
    private boolean isAddPermit;
    private boolean isEditPermit;


    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

//    @ManyToMany(mappedBy = "routeSalesOfficerList",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Route> routes = new HashSet<>();





}
