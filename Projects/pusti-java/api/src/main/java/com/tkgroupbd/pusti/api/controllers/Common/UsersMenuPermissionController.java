package com.tkgroupbd.pusti.api.controllers.Common;
import com.tkgroupbd.pusti.api.data.models.common.UsersMenuPermission;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.UsersMenuPermissionDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.Common.UsersMenuPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "UsersMenuPermission")
@RestController
@RequestMapping("/usersMenuPermission")
public class UsersMenuPermissionController {
    @Autowired
    UsersMenuPermissionService usersMenuPermissionService;


    @PostMapping("/addNewUsersMenuPermission")
    public ResponseEntity<MessageResponse> usersMenuPermissionSave(@RequestBody @Valid UsersMenuPermissionDTO usersMenuPermissionDTO) {
        MessageResponse usersMenuPermission = usersMenuPermissionService.saveUsersMenuPermission(usersMenuPermissionDTO);
        return new ResponseEntity<>(usersMenuPermission, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsersMenuPermission")
    @ResponseBody
    public ResponseEntity<List<UsersMenuPermission>> getAllUsersMenuPermission() {
        List<UsersMenuPermission> usersMenuPermissions = usersMenuPermissionService.getUsersMenuPermission();
        return new ResponseEntity<>(usersMenuPermissions, HttpStatus.OK);
    }

    @PutMapping("/updateUsersMenuPermission/{uMPid}")
    public ResponseEntity<Optional<UsersMenuPermission>> updateUsersMenuPermission(@PathVariable Integer uMPid,
                                                             @RequestBody @Valid UsersMenuPermissionDTO usersMenuPermissionDTO) {
        Optional<UsersMenuPermission> updateMenuItem = usersMenuPermissionService.updateUsersMenuPermission(uMPid, usersMenuPermissionDTO);
        return new ResponseEntity<Optional<UsersMenuPermission>>(updateMenuItem, HttpStatus.OK);
    }
}
