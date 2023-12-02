package com.tkgroupbd.pusti.api.data.payloads.dto.depot;

//import com.tkgroupbd.pusti.api.configs.validation.EnumValidator;

import com.tkgroupbd.pusti.api.configs.validation.EnumValidator;
import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.configs.validation.ValidPhoneNumber;
import com.tkgroupbd.pusti.api.data.models.enums.Department;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepotRequest extends BaseEntity {

    @NotBlank(message = "Invalid name : depot name can not be blank")
    @NotNull(message = "Invalid Name : depot name cant not be null")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String depotName;
    @Email(message = "Invalid email : it is not valid type email")
    private String email;
    @NotBlank(message = "Invalid phone : phone number can not be blank.")
    @NotNull(message = "Invalid phone : phone number can not be null.")
    @ValidPhoneNumber(minSize = 9, maxSize = 14, message = "This is not a valid phone number.")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Department department;
}
