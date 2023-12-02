package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.configs.validation.ValidPhoneNumber;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DistributorRequest extends BaseEntity {
    private int distributorId;
    @NotBlank(message = "  Invalid name: name cannot  be blank")
    @NotNull(message = "   Invalid name: name cannot be null")
    @Size(min = 3, max = 20, message = "  Name is max 20, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String name;
    private String erpId;
    @NotBlank(message = " Invalid address: address cannot  be blank")
    @NotNull(message = "Invalid address: address cannot be null")
    private String address;
    @NotBlank(message = "Invalid phone: cell number cannot  be blank")
    @NotNull(message = "Invalid name: cell number cannot be null")
    @ValidPhoneNumber(minSize = 9, maxSize = 14, message = "This is not a valid phone number.")
    private String mobile;
    private String proprietorName;
    private LocalDate proprietorDob;
    private boolean hasPrinter;
    private boolean hasPc;
    private boolean hasLiveApp;
    private boolean hasDirectSale;
    private LocalDate openingDate;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelation;
    private Upazila upazila;
    private Zone zone;
    private Depot depot;
}
