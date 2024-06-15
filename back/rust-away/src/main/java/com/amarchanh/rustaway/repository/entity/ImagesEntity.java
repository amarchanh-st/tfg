package com.amarchanh.rustaway.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "images")
public class ImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private BudgetEntity budget;

    @Column(name = "created_at")
    private LocalDateTime creationTime;
}
