package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.service.model.Budget;

public interface StatusService {

    Budget updateStatus(String newStatus, Long budgetId);
}
