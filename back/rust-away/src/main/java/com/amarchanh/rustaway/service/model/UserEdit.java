package com.amarchanh.rustaway.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEdit {

    private Long id;

    private String username;

    private String name;

    private String surname;

    private String oldPassword;

    private String newPassword;

    private String role;

    private String address;

    private LocalDate birthDate;

    private LocalDateTime creationDate;
}
