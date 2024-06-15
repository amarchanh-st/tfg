package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.UserService;
import com.amarchanh.rustaway.service.exceptions.DataMissmatchException;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.exceptions.UserAlreadyStoredException;
import com.amarchanh.rustaway.service.mapper.UserEntityMapper;
import com.amarchanh.rustaway.service.model.User;
import com.amarchanh.rustaway.service.model.UserEdit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDetailsService userDetailsService() {
        return username -> userRepository.findAllByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserEntity save(User entity) {
        final var user = userRepository.findAllByUsername(entity.getUsername());
        if(user.isPresent()) {
            throw new UserAlreadyStoredException("User already stored");
        }
        return userRepository.save(userEntityMapper.toEntity(entity));
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity user = userRepository.findAllByUsername(username).orElseThrow(NotFoundException::new);

        return userEntityMapper.toModel(user);
    }

    @Override
    public User updateUser(String username, UserEdit user) {
        UserEntity entity = userRepository.findAllByUsername(username).orElseThrow(NotFoundException::new);

        if(!entity.getPassword().equals(passwordEncoder.encode(user.getOldPassword()))) {
            throw new DataMissmatchException("Incorrect Password");
        }

        user.setId(entity.getId());
        user.setRole(entity.getRole());
        user.setCreationDate(entity.getCreationDate());
        UserEntity userToSave = userEntityMapper.toEntity(user);
        userToSave.setPassword(passwordEncoder.encode(user.getNewPassword()));
        return userEntityMapper.toModel(userRepository.save(userToSave));
    }

}
