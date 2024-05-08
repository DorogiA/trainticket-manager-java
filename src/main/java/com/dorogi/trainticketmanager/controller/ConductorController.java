package com.dorogi.trainticketmanager.controller;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConductorController {
    private final ConductorService conductorService;

    @GetMapping("/conductor/{id}")
    public ResponseEntity<SimpleMessageDTO> checkTicketValidity(@PathVariable String id) {
        SimpleMessageDTO simpleMessageDTO = conductorService.checkTicketValidity(id);
        return  ResponseEntity.status(200).body(simpleMessageDTO);
    }
}
