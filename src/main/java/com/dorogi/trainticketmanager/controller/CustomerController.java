package com.dorogi.trainticketmanager.controller;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.domain.dto.CustomerRegistrationDTO;
import com.dorogi.trainticketmanager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customer/get/{id}")
    public ResponseEntity<SimpleMessageDTO> searchCustomer(@PathVariable String id) {
        SimpleMessageDTO simpleMessageDTO = customerService.findCustomer(id);
        return ResponseEntity.status(200).body(simpleMessageDTO);
    }

    @PostMapping("/customer/add")
    public ResponseEntity<SimpleMessageDTO> addCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        SimpleMessageDTO simpleMessageDTO = customerService.saveNewCustomer(customerRegistrationDTO);
        return ResponseEntity.status(201).body(simpleMessageDTO);
    }
}
