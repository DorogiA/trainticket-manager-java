package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyTicketDTO {
    private Long customerId;
    private Long trainId;
    private Integer year;
    private Integer month;
    private Integer day;
}
