package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.UserService;
import com.amarchanh.rustaway.service.model.Jwt;
import com.amarchanh.rustaway.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findAllByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Jwt login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findAllByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return Jwt.builder().token(jwt).build();
    }

    @Override
    public Jwt signup(User user) {
        Optional<UserEntity> existingUser = userRepository.findAllByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            log.error("Username {} already exists in database.", user.getUsername());
            return null;
            // TODO: Return throw exception
            //throw new DataException("Username already exists in database.");
        }

        var newUser = UserEntity
                .builder()
                .name(user.getName())
                .surname(user.getSurname())
                .password(passwordEncoder.encode(user.getPassword()))
                .role("ROLE_CLIENT")
                .build();

        userRepository.save(newUser);
        var jwt = jwtService.generateToken(newUser);
        return Jwt.builder().token(jwt).build();
    }
}
