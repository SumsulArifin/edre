package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales;

import java.sql.Date;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "sales_officer")
public class SalesOfficer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesOfficerId;
    private String name;
    private Date dob;
    private String bloodGroup;
    private int districtId;
    private String email;
    private int companyId;
    private boolean isPustiOfficer;
    private Date joiningDate;
    private int designationId;
    private double basicSalary;
    private double houseRent;
    private double medicalAllowance;
    private double otherAllowance;
    private double travellingDailyAllowance;
    private double phoneBill;
    private double meetingTravellingAllowance;
    private double meetingJoiningAllowance;
    private String mobileNumber;
    private int sscPassingYear;
    private int highestDegreeId;
    private int bankId;
    private String bankAccountNumber;
    private double contributionPercentage;
    private String pcId;
    private String ecName;
    private String ecPhone;
    private String ecRelation;

    @ManyToOne
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

}
