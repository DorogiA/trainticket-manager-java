package com.dorogi.trainticketmanager.controller;

import com.dorogi.trainticketmanager.domain.dto.*;
import com.dorogi.trainticketmanager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer/add")
    public ResponseEntity<SimpleMessageDTO> addCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        SimpleMessageDTO simpleMessageDTO = customerService.saveNewCustomer(customerRegistrationDTO);
        return ResponseEntity.status(201).body(simpleMessageDTO);
    }

    @PostMapping("customer/ticket")
    public ResponseEntity<MessageWithTicketIDDTO> buyTicket(@RequestBody BuyTicketDTO buyTicketDTO) {
        MessageWithTicketIDDTO messageWithTicketIDDTO = customerService.buyTicketForCustomer(buyTicketDTO);
        return ResponseEntity.status(201).body(messageWithTicketIDDTO);
    }

    @PutMapping("customer/balance")
    public ResponseEntity<SimpleMessageDTO> addBalance(@RequestBody CustomerTransactionDTO customerTransactionDTO) {
        SimpleMessageDTO simpleMessageDTO = customerService.addBalanceToCustomer(customerTransactionDTO);
        return  ResponseEntity.status(200).body(simpleMessageDTO);
    }
}
