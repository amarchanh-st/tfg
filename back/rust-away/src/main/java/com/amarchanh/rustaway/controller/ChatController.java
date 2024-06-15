package com.amarchanh.rustaway.controller;

import com.amarchanh.rustaway.api.ChatsApi;
import com.amarchanh.rustaway.controller.mapper.BudgetMapper;
import com.amarchanh.rustaway.model.BudgetResponse;
import com.amarchanh.rustaway.model.ChatRequest;
import com.amarchanh.rustaway.service.ChatsService;
import com.amarchanh.rustaway.service.model.Budget;
import com.amarchanh.rustaway.service.model.Chat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@AllArgsConstructor
@Slf4j
public class ChatController implements ChatsApi {

    private final ChatsService chatsService;
    private final BudgetMapper budgetMapper;

    @Override
    public ResponseEntity<BudgetResponse> addChat(Integer budgetId, ChatRequest chatRequest) {
        log.info("Start adding chat to budget {}", budgetId);
        Budget budget = chatsService.addChat(budgetId, Chat.builder().username(chatRequest.getSender()).message(chatRequest.getMessage()).build());
        log.info("End adding chat to budget {}", budgetId);
        return ResponseEntity.ok(budgetMapper.toResponse(budget));
    }
}
