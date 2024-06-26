package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.domain.entity.CustomerEntity;
import com.dorogi.trainticketmanager.exceptions.NoEntityFoundException;
import com.dorogi.trainticketmanager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final CustomerRepository customerRepository;

    public SimpleMessageDTO findCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException(id.toString()));
        String message = "Customer with ID " + id + ": " + customerEntity.toString();
        SimpleMessageDTO response = SimpleMessageDTO
                .builder()
                .message(message)
                .build();
        log.info("Searched: " + message);
        return response;
    }
}
