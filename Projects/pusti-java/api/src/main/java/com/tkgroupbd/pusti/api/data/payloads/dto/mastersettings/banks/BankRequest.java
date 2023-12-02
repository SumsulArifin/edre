package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.banks;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.configs.validation.ValidPhoneNumber;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class BankRequest extends BaseEntity {

    @NotBlank(message = "  Bank name cannot be blank")
    @NotNull(message = "  Bank name cannot be  null")
    @Size(min = 3, max = 50, message = "  Bank Name is max 50, min is 3")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String bankName;
    @NotBlank(message = "  Bank accountant cannot be blank")
    @NotNull(message = "  Bank accountant cannot be  null")
    private String accountant;
    @NotBlank(message = "Invalid contact Number : phone number cant not be blank.")
    @NotNull(message = "Invalid contact Number : phone number cant not be null.")
    @ValidPhoneNumber(minSize = 9, maxSize = 14, message = "This is not a valid phone number.")
    private String contactNumber;
    private String bankAddress;
    private Distributor distributor;
}
