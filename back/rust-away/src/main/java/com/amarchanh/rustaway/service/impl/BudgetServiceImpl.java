package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.BudgetRepository;
import com.amarchanh.rustaway.repository.ImagesRepository;
import com.amarchanh.rustaway.repository.StatusRepository;
import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.ImagesEntity;
import com.amarchanh.rustaway.repository.entity.StatusEntity;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.BudgetService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.mapper.BudgetEntityMapper;
import com.amarchanh.rustaway.service.model.Budget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    private final BudgetEntityMapper budgetEntityMapper;

    private final StatusRepository statusRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;


    @Override
    public Budget createBudget(Budget budget) {
        BudgetEntity entity = budgetEntityMapper.toEntity(budget);
        Optional<StatusEntity> status = statusRepository.findByName("UNTOUCHED");
        if(status.isEmpty()){
            throw new NotFoundException("Status UNTOUCHED not present in Database");
        }
        Optional<UserEntity> userEntity = userRepository.findByUsername(budget.getUser().getUsername());
        if(userEntity.isEmpty()) {
            throw new NotFoundException("User "+budget.getUser().getUsername()+" not present in Database");
        }

        entity.setUser(userEntity.get());
        entity.setStatus(status.get());
        List<ImagesEntity> images = entity.getImages();
        entity.setImages(null);
        BudgetEntity savedEntity = budgetRepository.save(entity);
        if(Objects.nonNull(images)) {
            images.forEach(image -> image.setBudget(savedEntity));
            imagesRepository.saveAll(images);
            savedEntity.setImages(images);
        }

        return budgetEntityMapper.toModel(savedEntity);
    }

    @Override
    public Budget updatePrice(Long budgetId, Double price) {
        BudgetEntity entity = budgetRepository.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget not found in Database."));
        entity.setPrice(price);
        return budgetEntityMapper.toModel(budgetRepository.save(entity));
    }

    @Override
    public List<Budget> findAllByCriteria(String status, Integer limit, Integer skip, String order, UserDetails userDetails) {
        // TODO: Delete images from
        // TODO: En chats, definir campo isUser o similar para diferenciar mensajes del usuario y el personal de taller
        List<BudgetEntity> result;
        if(((UserEntity) userDetails).getRole().equals("ROLE_WORKER")) {
            result = budgetRepository.findAll();
        }
        else {
            result = budgetRepository.findAllByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new NotFoundException("No budgets found for user"));
        }
        return budgetEntityMapper.toModelList(result);
    }

    @Override
    public Budget findById(Integer id) {
        Optional<BudgetEntity> result = budgetRepository.findById(id.longValue());
        if(result.isEmpty()) {
            throw new NotFoundException("Budget with id "+id+" not found in Database");
        }
        return budgetEntityMapper.toModel(result.get());
    }
}
