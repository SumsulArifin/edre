package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas;

import com.tkgroupbd.pusti.api.configs.validation.NoNumberFirstCharacter;
import com.tkgroupbd.pusti.api.configs.validation.NoSpecialCharacterAccept;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OutletRequest extends BaseEntity {

    @NotBlank(message = " Invalid name: Outlet name can not be blank.")
    @NotNull(message = " Invalid name: Outlet name  can not null.")
    @Size(min = 3, max = 50, message = " Invalid Outlet name size : minimum  3 characters and maximum 50 characters will acceptable.")
    @NoNumberFirstCharacter
    @NoSpecialCharacterAccept
    private String outletName;
    private String address;
    @NotBlank(message = "Invalid : Contact Person cannot be blank.")
    @NotNull(message = "Invalid : Contact Person cannot be null.")
    @Size(min = 3, max = 50, message = "Acceptable size is : max 50, min  3.")
    private String contactPerson;
    @NotBlank(message = "Invalid : Mobile number cannot be blank.")
    @NotNull(message = "Invalid : Mobile number cannot be null.")
    @Size(min = 3, max = 50, message = "Acceptable size is : max 50, min  3.")
    private String mobile;
    private int salesPerMonth = 0;
    private double marketSize;
    private String keyOutlet;
    private int outletType;
    private int outletChannel;
    private boolean displayOutlet;
    private boolean displayOutletAmount;
    private double paidAmount;
    private String creditSales;
    private String shopType;
    private String comments;

    @NotBlank(message = "Invalid : Sales Group cannot be blank.")
    @NotNull(message = "Invalid : Sales Group cannot be null.")
    private String salesGroup;
    private double latitude;
    private double longitude;
    private Route route;
    private Distributor distributor;

}
