package com.amarchanh.rustaway.controller.mapper;

import com.amarchanh.rustaway.model.BudgetRequest;
import com.amarchanh.rustaway.model.BudgetResponse;
import com.amarchanh.rustaway.model.ChatResponse;
import com.amarchanh.rustaway.service.model.Budget;
import com.amarchanh.rustaway.service.model.Chats;
import com.amarchanh.rustaway.service.model.Images;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BudgetMapper {

    @Mapping(target = "comments", source = "chats", qualifiedByName = "toChatResponseList")
    @Mapping(target = "images", source = "images", qualifiedByName = "toImageString")
    BudgetResponse toResponse(Budget budget);

    List<BudgetResponse> toResponseList(List<Budget> budgetList);

    @Named("toResponseWithoutImages")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "comments", ignore = true)
    BudgetResponse toResponseWithoutImages(Budget budget);

    @IterableMapping(qualifiedByName = "toResponseWithoutImages")
    List<BudgetResponse> toResponseListWithoutImages(List<Budget> budgetList);

    @Mapping(target = "images", source = "images", qualifiedByName = "toImageList")
    @Mapping(target = "user.username", source = "user")
    Budget toModel(BudgetRequest request);

    @Named("toChatResponse")
    @Mapping(source = "message", target = "message")
    ChatResponse toChatResponse(Chats chat);


    @Named("toChatResponseList")
    @IterableMapping(qualifiedByName = "toChatResponse")
    List<ChatResponse> toChatResponseList(List<Chats> chatsList);

    @Named("toImageString")
    default String toImageString(Images image) {
        return image.getImage();
    }

    @Named("toImageStringList")
    @IterableMapping(qualifiedByName = "toImageString")
    List<String> toImageStringList(List<Images> imageList);

    @Named("toImage")
    default Images toImageModel(String image) {
        return Images.builder().image(image).creationTime(LocalDateTime.now()).build();
    }

    @Named("toImageList")
    @IterableMapping(qualifiedByName = "toImage")
    List<Images> toImageModelList(List<String> imageList);


}
