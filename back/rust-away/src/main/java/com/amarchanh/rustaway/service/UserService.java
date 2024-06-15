package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.exceptions.DataMissmatchException;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.exceptions.UserAlreadyStoredException;
import com.amarchanh.rustaway.service.model.User;
import com.amarchanh.rustaway.service.model.UserEdit;

public interface UserService {


    UserEntity save(User user) throws UserAlreadyStoredException;

    User getUserByUsername(String username) throws NotFoundException;

    User updateUser(String username, UserEdit user) throws NotFoundException, DataMissmatchException;
}
