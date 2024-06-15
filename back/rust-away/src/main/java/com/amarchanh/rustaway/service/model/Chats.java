package com.amarchanh.rustaway.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Chats {

    private Long id;

    private String message;

    private Boolean senderWorker;

    private LocalDateTime creationDate;
}
