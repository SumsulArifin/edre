package com.tkgroupbd.pusti.api.services.Common;
import com.tkgroupbd.pusti.api.data.models.common.UsersMenuPermission;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.UsersMenuPermissionDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsersMenuPermissionService {
    MessageResponse saveUsersMenuPermission(UsersMenuPermissionDTO usersMenuPermissionDTO);
    public Optional<UsersMenuPermission> updateUsersMenuPermission(int uMPid, UsersMenuPermissionDTO usersMenuPermissionDTO);
    public List<UsersMenuPermission> getUsersMenuPermission();

}
