package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleMessageDTO {
    private String message;
}
