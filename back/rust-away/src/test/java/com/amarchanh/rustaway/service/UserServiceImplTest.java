package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.exceptions.UserAlreadyStoredException;
import com.amarchanh.rustaway.service.impl.UserServiceImpl;
import com.amarchanh.rustaway.service.mapper.UserEntityMapper;
import com.amarchanh.rustaway.service.model.User;
import com.amarchanh.rustaway.service.model.UserEdit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    User user = User.builder()
            .name("user")
            .username("user")
            .id(1L)
            .password("password")
            .address("address")
            .birthDate(LocalDate.now()).build();

    UserEntity userEntity = UserEntity.builder()
            .name("user")
            .username("user")
            .id(1L)
            .password("password")
            .address("address")
            .birthDate(LocalDate.now()).build();


    @Test
    void shouldSaveUser_OK() {
        // When
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);


        // Then
        UserEntity result = userService.save(user);
        assertEquals(userEntity, result);
        verify(userRepository, times(1)).findByUsername("user");
        verify(userEntityMapper, times(1)).toEntity(user);
        verify(userRepository, times(1)).save(userEntity);

    }

    @Test
    void shouldSaveUser_KO() {
        // When
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));

        // Then
        assertThrows(UserAlreadyStoredException.class, () -> userService.save(user));
        verify(userRepository, times(1)).findByUsername("user");

    }

    @Test
    void shouldGetUserByUsername_OK() {
        // When
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toModel(userEntity)).thenReturn(user);

        // Then
        User result = userService.getUserByUsername("user");
        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername("user");
        verify(userEntityMapper, times(1)).toModel(userEntity);
    }

    @Test
    void shouldGetUserByUsername_KO() {
        // When
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> userService.getUserByUsername("user"));
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void shouldUpdateUser_OK() {
        // Given
        UserEdit userEdit = UserEdit.builder()
                .name("user")
                .username("user")
                .id(1L)
                .oldPassword("password")
                .newPassword("password")
                .address("address")
                .build();

        // When
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.encode("password")).thenReturn("password");
        when(userEntityMapper.toEntity(userEdit)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toModel(userEntity)).thenReturn(user);

        // Then
        User result = userService.updateUser("user", userEdit);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername("user");
        verify(passwordEncoder, times(2)).encode("password");
        verify(userEntityMapper, times(1)).toEntity(userEdit);
        verify(userRepository, times(1)).save(userEntity);
        verify(userEntityMapper, times(1)).toModel(userEntity);
    }
}
