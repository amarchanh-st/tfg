package com.amarchanh.rustaway.service.impl;

import com.amarchanh.rustaway.repository.BudgetRepository;
import com.amarchanh.rustaway.repository.ChatsRepository;
import com.amarchanh.rustaway.repository.UserRepository;
import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.ChatsEntity;
import com.amarchanh.rustaway.repository.entity.UserEntity;
import com.amarchanh.rustaway.service.ChatsService;
import com.amarchanh.rustaway.service.exceptions.NotFoundException;
import com.amarchanh.rustaway.service.mapper.BudgetEntityMapper;
import com.amarchanh.rustaway.service.model.Budget;
import com.amarchanh.rustaway.service.model.Chat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ChatsServiceImpl implements ChatsService {

    private final BudgetRepository budgetRepository;
    private final ChatsRepository chatsRepository;
    private final UserRepository userRepository;
    private final BudgetEntityMapper budgetEntityMapper;


    @Override
    public Budget addChat(Integer budgetId, Chat chat) {
        BudgetEntity budgetEntity = budgetRepository.findById(budgetId.longValue()).orElseThrow(() -> new NotFoundException("Budget with id not found"));
        UserEntity userEntity = userRepository.findByUsername(chat.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

        // Use this instead of mapper just to show the builder functionality
        ChatsEntity chatsEntity = ChatsEntity.builder()
                .sender(userEntity)
                .budget(budgetEntity)
                .message(chat.getMessage())
                .creationDate(LocalDateTime.now())
                .build();

        chatsRepository.save(chatsEntity);

        return budgetEntityMapper.toModel(budgetEntity) ;
    }
}
