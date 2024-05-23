package com.amarchanh.rustaway.controller;


import com.amarchanh.rustaway.api.UserApi;
import com.amarchanh.rustaway.controller.mapper.TokenMapper;
import com.amarchanh.rustaway.controller.mapper.UserMapper;
import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.model.UserRequest;
import com.amarchanh.rustaway.service.UserService;
import com.amarchanh.rustaway.service.model.Jwt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserApi {

    private final UserService userService;

    private final UserMapper userMapper;

    private final TokenMapper tokenMapper;

    @Override
    public ResponseEntity<TokenResponse> logIn(LoginRequest loginRequest) {
        try {
            log.info("Login request for user {}", loginRequest.getUsername());
            return ResponseEntity.ok(null);
        }
        /*
        catch ( e) {
            log.error("Login request ends in error caused by {}", e.getCause());
            return ResponseEntity.notFound().build();
        }
         */
        catch (Exception e) {
            log.error("Login request ends in error caused by " + e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<TokenResponse> signUp(UserRequest userRequest) {
        try {
            log.info("Signin request for user {}", userRequest);
            Jwt jwt = userService.signup(userMapper.toModel(userRequest));
            return ResponseEntity.ok(tokenMapper.toResponse(jwt));
        }
        catch (Exception e) {
            log.error("Sigin request ends in error caused by " + e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }
}
