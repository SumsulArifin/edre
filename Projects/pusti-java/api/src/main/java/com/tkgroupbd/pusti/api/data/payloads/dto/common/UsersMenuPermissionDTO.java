package com.tkgroupbd.pusti.api.data.payloads.dto.common;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsersMenuPermissionDTO {
    private Set<User> users;
}
