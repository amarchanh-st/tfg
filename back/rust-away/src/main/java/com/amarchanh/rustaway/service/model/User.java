package com.amarchanh.rustaway.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    private String name;

    private String surname;

    private String password;

    private String role;

    private LocalDateTime creationDate;
}
