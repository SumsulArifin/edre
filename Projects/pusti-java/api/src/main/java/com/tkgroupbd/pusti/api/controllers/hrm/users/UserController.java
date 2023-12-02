package com.tkgroupbd.pusti.api.controllers.hrm.users;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserRegistrationDTO;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users.UserDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.UserResponse;
import com.tkgroupbd.pusti.api.services.hrm.users.UserService;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> userRegister(
      @RequestBody @Valid UserRegistrationDTO request) {
    return ResponseEntity.ok(userService.registerUser(request));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponse> userLogin(
      @RequestBody UserDTO request) {
    return ResponseEntity.ok(userService.loginUser(request));
  }

  @GetMapping("/getAllUsers")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> userList = (List<User>) userService.getAllUsers();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Optional<User>> updateNotices(@PathVariable Integer id,
      @RequestBody UserRegistrationDTO registrationRequest) {
    Optional<User> modifiedUser = userService.updateUser(id, registrationRequest);
    return new ResponseEntity<Optional<User>>(modifiedUser, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
    userService.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/getById/{id}")
  public ResponseEntity<User> getDivisionById(@PathVariable("id") Integer id) {
    User objUser = userService.getUserById(id);
    return new ResponseEntity<>(objUser, HttpStatus.OK);
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, java.io.IOException {
    userService.refreshToken(request, response);
  }
}
