package com.amarchanh.rustaway.controller;

import com.amarchanh.rustaway.api.StatusApi;
import com.amarchanh.rustaway.controller.mapper.BudgetMapper;
import com.amarchanh.rustaway.model.BudgetResponse;
import com.amarchanh.rustaway.service.StatusService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.model.Budget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@AllArgsConstructor
@Slf4j
public class StatusController implements StatusApi {

    private final StatusService statusService;
    private final BudgetMapper budgetMapper;

    @Override
    public ResponseEntity<BudgetResponse> updateStatus(Integer budgetId, String newStatus) {
        Budget response;
        try {
            response = statusService.updateStatus(newStatus, budgetId.longValue());
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(budgetMapper.toResponse(response));
    }
}
