package com.amarchanh.rustaway.service.mapper;

import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {


    User toModel(UserEntity entity);

    UserEntity toEntity(User user);
}
