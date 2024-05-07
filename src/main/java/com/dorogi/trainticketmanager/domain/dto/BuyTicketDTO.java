package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyTicketDTO {
    private String customerId;
    private String trainId;
    private String year;
    private String month;
    private String day;
}
