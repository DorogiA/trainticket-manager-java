package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.domain.entity.TicketEntity;
import com.dorogi.trainticketmanager.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConductorService {
    private final TicketRepository ticketRepository;

    public SimpleMessageDTO checkTicketValidity(Long id) {
        TicketEntity ticketEntity = ticketRepository
                .findValidTicket(id, LocalDate.now())
                .orElse(null);
        String message;
        if (ticketEntity == null) {
            message = "NO VALID TICKET";
        }
        else {
            message = "VALID " + ticketEntity.toString();
        }
        log.info(id + " " + message);
        return SimpleMessageDTO
                .builder()
                .message(message)
                .build();
    }
}
