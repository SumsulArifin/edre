package com.tkgroupbd.pusti.api.data.models.entity.hrm.employees;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.Designation;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOrganization;
import com.tkgroupbd.pusti.api.data.models.enums.EmployeeType;
import io.micrometer.common.lang.Nullable;
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
@Table(name = "Employees")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employerId; // Formatted of the emploee provided by the company.

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    private String firstName;
    private String middleInitial;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfBirth;

    @Nullable
    private String bloodGroup;
    @Nullable
    private String nidNumber;
    private String mobileNumber;
    @Nullable
    private String email;
    private String address;
    @Nullable
    private String postalCode;
    private int upazilaId;
    private int districtId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfJoining;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfResignation;
    private double basicSalary;
    private double houseRent;
    private double medicalAllowance;
    private double internetBill;
    private double mobileBill;
    private double travellingDailyAllowance;
    private double meetingTravellingAllowance;
    private double meetingDailyAllowance;
    private double cityAllowance;
    private double otherAllowance;
    private double contributionPercentage;

    @Nullable
    private String emergencyContactName;
    @Nullable
    private String emergencyMobileNumber;
    @Nullable
    private String emergencyContactRelation;
    @Nullable
    private String backAccNumber;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "zoneId")
    private Zone zone;
    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "designationId")
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "divisionId")
    private Division division;
    @ManyToOne
    @JoinColumn(name = "bankId")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "salesOrgId")
    private SalesOrganization salesOrganization;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<EmployeeEducation> educationList = new ArrayList<>();
}
