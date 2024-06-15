package com.amarchanh.rustaway.controller;

import com.amarchanh.rustaway.api.BudgetApi;
import com.amarchanh.rustaway.controller.mapper.BudgetMapper;
import com.amarchanh.rustaway.model.BudgetRequest;
import com.amarchanh.rustaway.model.BudgetResponse;
import com.amarchanh.rustaway.model.PriceRequest;
import com.amarchanh.rustaway.service.BudgetService;
import com.amarchanh.rustaway.service.model.Budget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@AllArgsConstructor
@Slf4j
public class BudgetController implements BudgetApi {

    private final BudgetService budgetService;

    private final BudgetMapper budgetMapper;

    @Override
    public ResponseEntity<BudgetResponse> addBudget(BudgetRequest budgetRequest) {
        log.info("Request to addBudget with title {}", budgetRequest.getTitle());
        Budget budget = budgetService.createBudget(budgetMapper.toModel(budgetRequest));
        log.info("End of the request to addBudget with title {}", budgetRequest.getTitle());
        return ResponseEntity.ok(budgetMapper.toResponse(budget));
    }


    @Override
    public ResponseEntity<List<BudgetResponse>> findAllBudgets(String status, Integer limit, Integer skip, String order) {
        log.info("Request to retrieve all budgets with status: {}, limit: {}, skip: {}, order: {}", status, limit, skip, order);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        log.info(userDetails.getUsername());
        List<Budget> result = budgetService.findAllByCriteria(status, limit, skip, order, userDetails);
        return ResponseEntity.ok(budgetMapper.toResponseListWithoutImages(result));
    }

    @Override
    public ResponseEntity<BudgetResponse> findById(Integer id) {
        log.info("Request to retrieve budget with id: {}",id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        log.info(userDetails.getUsername());
        Budget result = budgetService.findById(id);
        BudgetResponse response = budgetMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BudgetResponse> updatePriceBudget(Integer budgetId, PriceRequest priceRequest) {
        log.info("Request to addPrice to budget with id: {}", budgetId);
        Budget budget = budgetService.updatePrice(Long.valueOf(budgetId), priceRequest.getPrice());

        return ResponseEntity.ok(budgetMapper.toResponse(budget));
    }
}
