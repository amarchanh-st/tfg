package com.amarchanh.rustaway.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "budget")
public class BudgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String brand;

    private String model;

    private String description;

    private Double price;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusEntity status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "budget_id")
    private List<ChatsEntity> chats;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "budget")
    private List<ImagesEntity> images;

    private LocalDate estimatedDate;

    @Column(name = "created_at")
    private LocalDateTime creationDate;


}
