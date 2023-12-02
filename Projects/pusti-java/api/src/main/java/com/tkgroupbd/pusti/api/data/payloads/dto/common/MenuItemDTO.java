package com.tkgroupbd.pusti.api.data.payloads.dto.common;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.common.UsersMenuPermission;
import com.tkgroupbd.pusti.api.data.models.enums.GroupId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MenuItemDTO extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private GroupId groupId;
    private String title;
    private Set<UsersMenuPermission> usersMenuPermissions;
}
