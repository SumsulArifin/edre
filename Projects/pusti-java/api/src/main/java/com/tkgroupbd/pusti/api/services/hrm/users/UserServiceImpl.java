package com.tkgroupbd.pusti.api.services.hrm.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.Token;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.enums.TokenType;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserRegistrationDTO;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.UserResponse;
import com.tkgroupbd.pusti.api.data.repositories.hrm.users.TokenRepository;
import com.tkgroupbd.pusti.api.data.repositories.hrm.users.UserRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserResponse registerUser(UserRegistrationDTO request) {
        User objNewUser = new User();
        objNewUser.setEmployee(request.getEmployee());
        objNewUser.setUserName(request.getUserName());
        objNewUser.setPassword(passwordEncoder.encode(request.getPassword()));
        objNewUser.setAssignedPrivilege(request.getAssignedPrivileges());
        objNewUser.setRole(request.getRole());
        objNewUser.setStatus(request.isUserStatus());

        var savedUser = userRepository.save(objNewUser);
        var jwtToken = jwtService.generateToken(objNewUser);
        var refreshToken = jwtService.generateRefreshToken(objNewUser);
        this.saveUserToken(savedUser, jwtToken);

        return UserResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public UserResponse loginUser(UserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()));
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return UserResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByUserName(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = UserResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> updateUser(Integer userId, UserRegistrationDTO userRegistrationRequest) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User objModifyingUser = new User();
            objModifyingUser.setEmployee(userRegistrationRequest.getEmployee());
            objModifyingUser.setUserName(userRegistrationRequest.getUserName());
            objModifyingUser.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
            objModifyingUser.setAssignedPrivilege(userRegistrationRequest.getAssignedPrivileges());
            objModifyingUser.setRole(userRegistrationRequest.getRole());
            objModifyingUser.setStatus(userRegistrationRequest.isUserStatus());

            userRepository.save(objModifyingUser);
        } else {
            throw new ResourceNotFoundException("User", "userId", userId);
        }

        return result;
    }

    @Override
    public Optional<User> changeStatus(UserRegistrationDTO userRegistrationRequest) {
        Optional<User> objUser = userRepository.findById(userRegistrationRequest.getUserId());
        if (objUser.isEmpty()) {
            throw new ResourceNotFoundException("User", "userId", userRegistrationRequest.getUserId());
        } else
            objUser.get().setStatus(userRegistrationRequest.isUserStatus());
        ;
        userRepository.save(objUser.get());
        return objUser;
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }
}
