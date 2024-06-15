package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.service.model.Budget;
import com.amarchanh.rustaway.service.model.Chat;

public interface ChatsService {

    Budget addChat( Integer budgetId,Chat chat);
}
