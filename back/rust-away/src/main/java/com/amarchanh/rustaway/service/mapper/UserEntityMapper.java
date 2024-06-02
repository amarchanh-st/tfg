package com.amarchanh.rustaway.service.mapper;

import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.model.User;
import com.amarchanh.rustaway.service.model.UserEdit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {


    User toModel(UserEntity entity);

    @Mapping(source="newPassword", target = "password")
    UserEntity toEntity(UserEdit userEdit);

    UserEntity toEntity(User user);
}
