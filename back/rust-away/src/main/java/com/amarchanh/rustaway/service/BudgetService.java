package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.service.model.Budget;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface BudgetService {

    Budget createBudget(Budget budget);

    Budget updatePrice(Long budgetId, Double price);

    List<Budget> findAllByCriteria(String status, Integer limit, Integer skip, String order, UserDetails userDetails);

    Budget findById(Integer id);

}
