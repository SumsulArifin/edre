package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
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
@Table(name = "outlets")
public class Outlet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int outletId;
    @Column(length = 50, name = "outlet_name", nullable = false)
    private String outletName;
    private String address;
    @Column(nullable = false)
    private String contactPerson;
    @Column(nullable = false, length = 50)
    private String mobile;
    @Column(nullable = false, length = 50)
    private int salesPerMonth = 0;
    @Column(nullable = false, length = 50 )
    private double marketSize;
    @Column(nullable = true, length = 50 )
    private String keyOutlet;
    @Column(nullable = false, length = 50 )
    private int outletType;
    @Column(nullable = false, length = 50 )
    private int outletChannel;
    private boolean displayOutlet;
    private boolean displayOutletAmount;
    @Column(nullable = false, length = 50 )
    private double paidAmount;
    @Column(nullable = true, length = 50 )
    private String CreditSales;
    @Column(nullable = true, length = 50 )
    private String shopType;
    private String comments;
    @Column(nullable = false, length = 50 )
    private String salesGroup;
    @Column(length = 50, nullable = false)
    private double latitude;
    @Column(length = 50, nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "routeId" )
    private Route route;

    @ManyToOne
    @JoinColumn(name = "distributorId")
    private Distributor distributor;
}
