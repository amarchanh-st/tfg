package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.model.UserRequest;
import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.AuthenticationService;
import com.amarchanh.rustaway.service.model.Jwt;
import com.amarchanh.rustaway.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Jwt singup(User request) throws Exception {
        // TODO: Sacar a mapper
        var user = User
                .builder()
                .name(request.getName())
                .username(request.getUsername())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_CLIENT")
                .creationDate(LocalDateTime.now())
                .build();

        final var storedUser = userService.save(user);
        var jwt = jwtService.generateToken(storedUser);
        return Jwt.builder().token(jwt).build();
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findAllByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return TokenResponse.builder().token(jwt).build();
    }
}
