package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")
public class Bank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bankId;
    @Column(length = 50, nullable = false)
    private String bankName;
    @Column(length = 50, nullable = false)
    private String accountant;
    @Column(length = 50, nullable = false)
    private String contactNumber;
    private String bankAddress;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
}
