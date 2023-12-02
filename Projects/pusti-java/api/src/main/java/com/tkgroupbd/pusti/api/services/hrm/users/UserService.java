package com.tkgroupbd.pusti.api.services.hrm.users;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserRegistrationDTO;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    public UserResponse registerUser(UserRegistrationDTO request);

    public UserResponse loginUser(UserDTO request);

    public List<User> getAllUsers();

    Optional<User> updateUser(Integer id, UserRegistrationDTO userRegistrationRequest);

    Optional<User> changeStatus(UserRegistrationDTO userRegistrationRequest);

    void deleteUserById(Integer id);

    User getUserById(Integer id);

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
