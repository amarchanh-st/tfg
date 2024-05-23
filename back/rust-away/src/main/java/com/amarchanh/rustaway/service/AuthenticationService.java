package com.amarchanh.rustaway.service;


import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.service.model.Jwt;
import com.amarchanh.rustaway.service.model.User;

public interface AuthenticationService {

    public Jwt singup(User request) throws Exception;

    public TokenResponse login(LoginRequest request);
}
