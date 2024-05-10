package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerTransactionDTO {
    private Long id;
    private Integer amount;
}
