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
    public ResponseEntity<String> searchCustomer(@PathVariable String id) {
        String customer = customerService.findCustomer(id);
        return ResponseEntity.status(200).body(customer);
    }

    @PostMapping("/customer/add")
    public ResponseEntity<SimpleMessageDTO> addCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        SimpleMessageDTO simpleMessageDTO = customerService.saveNewCustomer(customerRegistrationDTO);
        return ResponseEntity.status(201).body(simpleMessageDTO);
    }
}
