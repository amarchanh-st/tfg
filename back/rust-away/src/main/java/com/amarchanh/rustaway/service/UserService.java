package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.model.LoginRequest;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.model.Jwt;
import com.amarchanh.rustaway.service.model.User;

public interface UserService {


    UserEntity save(User user);
}
