package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Table(name = "Distributors")
public class Distributor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distributorId;
    private String name;
    private String erpId;
    private String address;
    private String mobile;
    private String proprietorName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate proprietorDob;
    private boolean hasPrinter;
    private boolean hasPc;
    private boolean hasLiveApp;
    private boolean hasDirectSale;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelation;

    @ManyToOne
    @JoinColumn(name = "upazilaId")
    private Upazila upazila;

    @ManyToOne
    @JoinColumn(name = "zoneId")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "depotId")
    private Depot depot;
}
