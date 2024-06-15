package com.amarchanh.rustaway.repository;

import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {

    @Query(value = "SELECT budget FROM BudgetEntity budget WHERE budget.user.username = :username")
    Optional<List<BudgetEntity>> findAllByUsername(@Param(value = "username") String username);
}
