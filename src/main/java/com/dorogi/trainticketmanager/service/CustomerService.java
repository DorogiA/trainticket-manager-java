package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.SimpleMessageDTO;
import com.dorogi.trainticketmanager.domain.dto.CustomerRegistrationDTO;
import com.dorogi.trainticketmanager.domain.entity.CustomerEntity;
import com.dorogi.trainticketmanager.exceptions.EmailAlreadyPresentException;
import com.dorogi.trainticketmanager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public String findCustomer(String id) {
        CustomerEntity customerEntity = customerRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException());
        return customerEntity.toString();
    }

    @Transactional
    public SimpleMessageDTO saveNewCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        if (isEmailAlreadyPresent(customerRegistrationDTO.getEmail())) {
            throw new EmailAlreadyPresentException(customerRegistrationDTO.getEmail());
        }
        CustomerEntity customerEntity = createCustomerEntity(customerRegistrationDTO);
        customerEntity = customerRepository.save(customerEntity);
        String message = "New customer " + customerEntity.getName() + " saved with ID " + customerEntity.getId();
        SimpleMessageDTO response = SimpleMessageDTO
                .builder()
                .message(message)
                .build();
        log.info(message);
        return response;
    }

    private Boolean isEmailAlreadyPresent(String email) {
        return customerRepository
                .findByEmail(email)
                .isPresent();
    }

    private CustomerEntity createCustomerEntity(CustomerRegistrationDTO customerRegistrationDTO) {
        Integer starterBalance = 1;
        return CustomerEntity
                .builder()
                .name(customerRegistrationDTO.getName())
                .email(customerRegistrationDTO.getEmail())
                .balance(starterBalance)
                .build();
    }

}
