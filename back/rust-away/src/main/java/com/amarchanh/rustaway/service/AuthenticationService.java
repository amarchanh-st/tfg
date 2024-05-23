package com.amarchanh.rustaway.service;


import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.model.UserRequest;

public interface AuthenticationService {

    public TokenResponse singup(UserRequest request) throws Exception;

    public TokenResponse login(LoginRequest request);
}
