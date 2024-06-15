package com.amarchanh.rustaway.repository;

import com.amarchanh.rustaway.repository.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    Optional<StatusEntity> findByName(String name);
}
