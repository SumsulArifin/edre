package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.sales;

import java.sql.Date;

import com.tkgroupbd.pusti.api.configs.validation.ValidPhoneNumber;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalesOfficerRequest extends BaseEntity {
    private int salesOfficerId;
    @NotBlank(message = " name cannot be blank")
    @NotNull(message = " name cannot be null")
    @Size(min = 3, max = 20, message = " Name is max 20, min is 3")
    private String name;
    @Past(message = "Date of birth must be in the past")
    private Date dob;
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
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
    @ValidPhoneNumber(minSize = 9, maxSize = 14, message = "This is not a valid phone number.")
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
    private Distributor distributor;

    public boolean isStatus() {
        return false;
    }
}
