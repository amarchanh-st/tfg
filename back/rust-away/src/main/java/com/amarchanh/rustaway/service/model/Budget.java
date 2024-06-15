package com.amarchanh.rustaway.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    private Long id;

    private String title;

    private String brand;

    private String model;

    private String description;

    private Double price;

    private String status;

    private User user;

    private List<Chats> chats;

    private List<Images> images;

    private LocalDate estimatedDate;

    private LocalDateTime creationDate;

}
