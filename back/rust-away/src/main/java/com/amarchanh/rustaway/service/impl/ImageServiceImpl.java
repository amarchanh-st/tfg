package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.BudgetRepository;
import com.amarchanh.rustaway.repository.ImagesRepository;
import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.ImagesEntity;
import com.amarchanh.rustaway.service.ImageService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.mapper.BudgetEntityMapper;
import com.amarchanh.rustaway.service.model.Budget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final BudgetRepository budgetRepository;
    private final ImagesRepository imagesRepository;
    private final BudgetEntityMapper budgetEntityMapper;

    @Override
    public Budget addImage(Long budgetId, List<String> images) {
        BudgetEntity budgetEntity = budgetRepository.findById(budgetId).orElseThrow( () -> new NotFoundException("Budget not found in database."));

        List<ImagesEntity> imagesList = new ArrayList<>();
        if(Objects.nonNull(images)) {
            images.forEach(image -> {
                ImagesEntity entity = ImagesEntity.builder()
                        .budget(budgetEntity)
                        .image(image)
                        .creationTime(LocalDateTime.now())
                        .build();
                imagesList.add(entity);
            });

            imagesRepository.saveAll(imagesList);
        }

        return budgetEntityMapper.toModel(budgetEntity);
    }
}
