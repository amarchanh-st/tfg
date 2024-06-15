package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.BudgetRepository;
import com.amarchanh.rustaway.repository.StatusRepository;
import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.StatusEntity;
import com.amarchanh.rustaway.service.StatusService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.mapper.BudgetEntityMapper;
import com.amarchanh.rustaway.service.model.Budget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StatusServiceImpl implements StatusService {

    private final BudgetRepository budgetRepository;

    private final StatusRepository statusRepository;

    private final BudgetEntityMapper budgetEntityMapper;

    @Override
    public Budget updateStatus(String newStatus, Long budgetId) {
        BudgetEntity budget = budgetRepository.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget with id "+ budgetId +" not found in Database."));

        StatusEntity statusEntity = statusRepository.findByName(newStatus).orElseThrow(() -> new NotFoundException("Status with name " +newStatus+ " not found in Database."));

        budget.setStatus(statusEntity);

        return budgetEntityMapper.toModel(budgetRepository.save(budget));
    }
}
