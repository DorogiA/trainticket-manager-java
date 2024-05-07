package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageWithTicketIDDTO {
    private String balanceMessage;
    private String ticketMessage;
}
