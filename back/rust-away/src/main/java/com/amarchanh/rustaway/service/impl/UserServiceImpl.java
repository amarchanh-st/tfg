package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.UserService;
import com.amarchanh.rustaway.service.mapper.UserEntityMapper;
import com.amarchanh.rustaway.service.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    public UserDetailsService userDetailsService() {
        return username -> userRepository.findAllByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserEntity save(User entity) throws Exception {
        final var user = userRepository.findAllByUsername(entity.getUsername());
        if(user.isPresent()) {
            // TODO: Change exception
            throw new Exception("User already stores");
        }
        return userRepository.save(userEntityMapper.toEntity(entity));
    }


}
