package com.amarchanh.rustaway.controller;


import com.amarchanh.rustaway.api.UserApi;
import com.amarchanh.rustaway.controller.mapper.TokenMapper;
import com.amarchanh.rustaway.controller.mapper.UserMapper;
import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.model.UserRequest;
import com.amarchanh.rustaway.service.AuthenticationService;
import com.amarchanh.rustaway.service.model.Jwt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserApi {

    private final AuthenticationService authenticationService;

    private final UserMapper userMapper;

    private final TokenMapper tokenMapper;

    @Override
    public ResponseEntity<TokenResponse> logIn(LoginRequest loginRequest) {
        try {
            log.info("Login request for user {}", loginRequest.getUsername());
            return ResponseEntity.ok(authenticationService.login(loginRequest));

                    //TokenResponse.builder().token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c").build());
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
            log.info("Signup request for user {}", userRequest);
            Jwt jwt = authenticationService.singup(userMapper.toModel(userRequest));
            return ResponseEntity.ok(tokenMapper.toResponse(jwt));
        }
        catch (Exception e) {
            log.error("Signup request ends in error caused by " + e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }
}
