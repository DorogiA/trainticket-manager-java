package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRegistrationDTO {
    private String name;
    private String email;
}
