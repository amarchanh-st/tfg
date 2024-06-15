package com.amarchanh.rustaway.controller;


import com.amarchanh.rustaway.api.UserApi;
import com.amarchanh.rustaway.controller.mapper.TokenMapper;
import com.amarchanh.rustaway.controller.mapper.UserMapper;
import com.amarchanh.rustaway.model.*;
import com.amarchanh.rustaway.service.AuthenticationService;
import com.amarchanh.rustaway.service.UserService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.model.Jwt;
import com.amarchanh.rustaway.service.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserApi {

    private final AuthenticationService authenticationService;

    private final UserMapper userMapper;

    private final TokenMapper tokenMapper;

    private final UserService userService;


    @Override
    public ResponseEntity<TokenResponse> signUp(UserRequest userRequest) {
        try {
            log.info("Signup request for user {}", userRequest);
            Jwt jwt = authenticationService.singup(userMapper.toModel(userRequest));
            return ResponseEntity.ok(tokenMapper.toResponse(jwt));
        }
        catch (Exception e) {
            log.error("Signup request ends in error caused by " + e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<TokenResponse> logIn(LoginRequest loginRequest) {
        try {
            log.info("Login request for user {}", loginRequest.getUsername());
            return ResponseEntity.ok(authenticationService.login(loginRequest));
        }
        catch (NotFoundException e) {
            log.error("Login request failed in error caused by {}", e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<UserResponse> getUser(String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(userMapper.toResponse(user));
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // TODO: Endpoint to avance statuses

    // TODO: Method to add comments

    @Override
    public ResponseEntity<UserResponse> editUser(String username, UserEditRequest request) {
        try {
            User user = userService.updateUser(username, userMapper.toModel(request));
            return ResponseEntity.ok(userMapper.toResponse(user));
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
