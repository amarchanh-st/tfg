package com.amarchanh.rustaway.service;

import com.amarchanh.rustaway.repository.BudgetRepository;
import com.amarchanh.rustaway.repository.ImagesRepository;
import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.ImagesEntity;
import com.amarchanh.rustaway.service.impl.ImageServiceImpl;
import com.amarchanh.rustaway.service.mapper.BudgetEntityMapper;
import com.amarchanh.rustaway.service.model.Budget;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImagesServiceImplTest {

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private ImagesRepository imagesRepository;

    @Mock
    private BudgetEntityMapper budgetEntityMapper;


    @Test
    void shouldAddImage() {
        // Given
        BudgetEntity budgetEntity = BudgetEntity.builder()
                .id(1L)
                .brand("Brand")
                .model("Model")
                .build();

        Budget budget = Budget.builder()
                .id(1L)
                .brand("Brand")
                .model("Model")
                .build();

        ImagesEntity imagesEntity = ImagesEntity.builder()
                .image("IMAGES IN BASE64")
                .budget(budgetEntity)
                .creationTime(LocalDateTime.now())
                .build();
        List<ImagesEntity> imageList = new ArrayList<>();
        imageList.add(imagesEntity);

        // When
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budgetEntity));
        when(imagesRepository.saveAll(any())).thenReturn(any());
        when(budgetEntityMapper.toModel(budgetEntity)).thenReturn(budget);

        // Then
        Budget result = imageService.addImage(1L, List.of("IMAGES IN BASE64"));
        verify(budgetRepository, times(1)).findById(1L);
        verify(imagesRepository, times(1)).saveAll(any());
        verify(budgetEntityMapper, times(1)).toModel(budgetEntity);
    }


}
