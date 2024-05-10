package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.*;
import com.dorogi.trainticketmanager.domain.entity.CustomerEntity;
import com.dorogi.trainticketmanager.domain.entity.TicketEntity;
import com.dorogi.trainticketmanager.exceptions.*;
import com.dorogi.trainticketmanager.repository.CustomerRepository;
import com.dorogi.trainticketmanager.repository.TicketRepository;
import com.dorogi.trainticketmanager.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;

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

    @Transactional
    public SimpleMessageDTO addBalanceToCustomer(CustomerTransactionDTO customerTransactionDTO) {
        CustomerEntity customerEntity = customerRepository
                .findById(customerTransactionDTO.getId())
                .orElseThrow(() -> new NoEntityFoundException("Customer ID: " + customerTransactionDTO.getId()));
        customerEntity.setBalance(customerEntity.getBalance() + customerTransactionDTO.getAmount());
        customerRepository.save(customerEntity);
        String message = "Added balance to customer with ID " + customerTransactionDTO.getId() + ". New balance: " + customerEntity.getBalance();
        log.info(message);
        return SimpleMessageDTO
                .builder()
                .message(message)
                .build();
    }

    public MessageWithTicketIDDTO buyTicketForCustomer(BuyTicketDTO buyTicketDTO) {
        if (!checkIfCustomerIsPresent(buyTicketDTO.getCustomerId())) {
            throw new NoEntityFoundException(buyTicketDTO.getCustomerId().toString());
        }
        if (!checkIfTrainIsPresent(buyTicketDTO.getTrainId())) {
            throw new NoEntityFoundException(buyTicketDTO.getTrainId().toString());
        }
        checkIfDatesAreAcceptable(buyTicketDTO);
        String balanceMessage = removeBalance(buyTicketDTO.getCustomerId());
        String ticketMessage = buyTicket(buyTicketDTO);
        log.info("Ticket bought");
        return MessageWithTicketIDDTO
                .builder()
                .balanceMessage(balanceMessage)
                .ticketMessage(ticketMessage)
                .build();
    }

    private Boolean isEmailAlreadyPresent(String email) {
        log.info("Searching emails");
        return customerRepository
                .findByEmail(email)
                .isPresent();
    }

    private CustomerEntity createCustomerEntity(CustomerRegistrationDTO customerRegistrationDTO) {
        Integer starterBalance = 1;
        log.info("Creating new customer");
        return CustomerEntity
                .builder()
                .name(customerRegistrationDTO.getName())
                .email(customerRegistrationDTO.getEmail())
                .balance(starterBalance)
                .build();
    }

    private Boolean checkIfCustomerIsPresent(Long customerId) {
        log.info("Checking if customer is present");
        return customerRepository
                .findById(customerId)
                .isPresent();
    }

    private Boolean checkIfTrainIsPresent(Long trainId) {
        log.info("Checking if train is present");
        return trainRepository
                .findById(trainId)
                .isPresent();

    }

    private void checkIfDatesAreAcceptable(BuyTicketDTO buyTicketDTO) {
        log.info("Checking if dates are acceptable");
        try {
            Year.of(buyTicketDTO.getYear())
                    .atMonth(buyTicketDTO.getMonth())
                    .atDay(buyTicketDTO.getDay());
        } catch (DateTimeException dateTimeException) {
            throw new DateNotAcceptableException(buyTicketDTO.getYear() + " or " + buyTicketDTO.getMonth() + " or " + buyTicketDTO.getDay());
        }
    }

    @Transactional
    private String removeBalance(Long customerId) {
        CustomerEntity customerEntity = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new NoEntityFoundException("Customer ID: " + customerId));
        if (customerEntity.getBalance() < 1) {
            throw new NotEnoughBalanceException(customerId.toString());
        }
        customerEntity.setBalance(customerEntity.getBalance() - 1);
        customerRepository.save(customerEntity);
        String message = "Removed 1 from balance of customer with ID " + customerId + ". Remaining balance: " + customerEntity.getBalance();
        log.info(message);
        return message;
    }

    @Transactional
    private String buyTicket(BuyTicketDTO buyTicketDTO) {
        TicketEntity ticketEntity = TicketEntity
                .builder()
                .customerId(buyTicketDTO.getCustomerId())
                .trainId(buyTicketDTO.getTrainId())
                .purchaseTime(LocalDate.now())
                .validity(Year.of(buyTicketDTO.getYear())
                        .atMonth(buyTicketDTO.getMonth())
                        .atDay(buyTicketDTO.getDay()))
                .build();
        ticketEntity = ticketRepository.save(ticketEntity);
        String message = "Added new ticket to database with values " + ticketEntity.toString() + ". You can use your ID at inspection.";
        log.info(message);
        return message;
    }

}
