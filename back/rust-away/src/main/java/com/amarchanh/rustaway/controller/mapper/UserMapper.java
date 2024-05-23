package com.amarchanh.rustaway.controller.mapper;

import com.amarchanh.rustaway.model.UserRequest;
import com.amarchanh.rustaway.model.UserResponse;
import com.amarchanh.rustaway.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     User toModel(UserRequest request);

     UserResponse toResponse(User user);
}
