package com.dorogi.trainticketmanager.controller;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/manager/get-customer/{id}")
    public ResponseEntity<SimpleMessageDTO> searchCustomer(@PathVariable Long id) {
        SimpleMessageDTO simpleMessageDTO = managerService.findCustomer(id);
        return ResponseEntity.status(200).body(simpleMessageDTO);
    }
}
