package com.amarchanh.rustaway.controller;

import com.amarchanh.rustaway.api.ImagesApi;
import com.amarchanh.rustaway.controller.mapper.BudgetMapper;
import com.amarchanh.rustaway.model.BudgetResponse;
import com.amarchanh.rustaway.model.ImageRequest;
import com.amarchanh.rustaway.service.ImageService;
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
public class ImagesController implements ImagesApi {

    private final ImageService imageService;
    private final BudgetMapper budgetMapper;

    @Override
    public ResponseEntity<BudgetResponse> addImage(Integer budgetId, ImageRequest imageRequest) {
        log.info("Start adding image to budget with id: {}", budgetId);
        Budget budget = imageService.addImage(budgetId.longValue(), imageRequest.getImages());
        log.info("End adding image to budget with id: {}", budgetId);
        return ResponseEntity.ok(budgetMapper.toResponse(budget));
    }
}
