package com.amarchanh.rustaway.controller.mapper;

import com.amarchanh.rustaway.model.TokenResponse;
import com.amarchanh.rustaway.service.model.Jwt;
import org.mapstruct.Mapper;

@Mapper
public interface TokenMapper {

    TokenResponse toResponse(Jwt jwt);
}
