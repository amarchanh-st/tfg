package com.amarchanh.rustaway.service.mapper;

import com.amarchanh.rustaway.repository.entity.BudgetEntity;
import com.amarchanh.rustaway.repository.entity.ChatsEntity;
import com.amarchanh.rustaway.repository.entity.ImagesEntity;
import com.amarchanh.rustaway.service.model.Budget;
import com.amarchanh.rustaway.service.model.Chats;
import com.amarchanh.rustaway.service.model.Images;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BudgetEntityMapper {

    @Mapping(target = "status", expression = "java(entity.getStatus() != null ? entity.getStatus().getName() : \"\")")
    @Mapping(target = "chats", source = "chats", qualifiedByName = "toChatsModelList")
    @Mapping(target = "images", source = "images", qualifiedByName = "toImagesModelList")
    Budget toModel(BudgetEntity entity);

    @Mapping(target="status", ignore = true)
    BudgetEntity toEntity(Budget budget);

    List<Budget> toModelList(List<BudgetEntity> entityList);


    @Named("toChatsModel")
    @Mapping( target = "senderWorker", expression = "java((\"ROLE_WORKER\").equals(entity.getSender().getRole()))" )
    Chats toChatsModel(ChatsEntity entity);


    @Named("toChatsModelList")
    @IterableMapping(qualifiedByName = "toChatsModel")
    List<Chats> toChatsModelList(List<ChatsEntity> entityList);

    @Named("toImagesModel")
    Images toImagesModel(ImagesEntity entity);

    @Named("toImagesModelList")
    @IterableMapping(qualifiedByName = "toImagesModel")
    List<Images> toImagesModelList(List<ImagesEntity> entityList);


}
