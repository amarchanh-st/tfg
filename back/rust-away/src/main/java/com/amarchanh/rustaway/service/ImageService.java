package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.service.model.Budget;

import java.util.List;

public interface ImageService {

    Budget addImage(Long budgetId, List<String> images);
}
