package com.tkgroupbd.pusti.api.services.Common;

import com.tkgroupbd.pusti.api.data.models.common.MenuItem;
import com.tkgroupbd.pusti.api.data.models.common.UsersMenuPermission;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.UsersMenuPermissionDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.common.UsersMenuPermissionRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersMenuPermissionServiceImpl implements  UsersMenuPermissionService {
    @Autowired
    UsersMenuPermissionRepository usersMenuPermissionRepository;
    @Override
    public MessageResponse saveUsersMenuPermission(UsersMenuPermissionDTO usersMenuPermissionDTO) {
        try {
            UsersMenuPermission usersMenuPermission=new UsersMenuPermission();
            usersMenuPermission.setUsers(usersMenuPermissionDTO.getUsers());
            usersMenuPermissionRepository.save(usersMenuPermission);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<UsersMenuPermission> updateUsersMenuPermission(int uMPid, UsersMenuPermissionDTO usersMenuPermissionDTO) {
        Optional<UsersMenuPermission> result = usersMenuPermissionRepository.findById(uMPid);
        if (result.isPresent()) {
            UsersMenuPermission usersMenuPermission=result.get();
            usersMenuPermission.setUsers(usersMenuPermissionDTO.getUsers());
            usersMenuPermissionRepository.save(usersMenuPermission);
        } else {
            throw new ResourceNotFoundException("UsersMenuPermission", "uMPid", uMPid);
        }
        return result;
    }

    @Override
    public List<UsersMenuPermission> getUsersMenuPermission() {
        return usersMenuPermissionRepository.findAll();
    }
}
