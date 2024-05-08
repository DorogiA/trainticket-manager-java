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
                .findById(Long.parseLong(customerTransactionDTO.getId()))
                .orElseThrow(() -> new NoEntityFoundException("Customer ID: " + customerTransactionDTO.getId()));
        customerEntity.setBalance(customerEntity.getBalance() + Integer.parseInt(customerTransactionDTO.getAmount()));
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
            throw new NoEntityFoundException(buyTicketDTO.getCustomerId());
        }
        if (!checkIfTrainIsPresent(buyTicketDTO.getTrainId())) {
            throw new NoEntityFoundException(buyTicketDTO.getTrainId());
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

    private Boolean checkIfCustomerIsPresent(String customerId) {
        log.info("Checking if customer is present");
        try {
            return customerRepository
                    .findById(Long.parseLong(customerId))
                    .isPresent();
        } catch (NumberFormatException numberFormatException) {
            throw new IdNotAcceptableException(customerId);
        }
    }

    private Boolean checkIfTrainIsPresent(String trainId) {
        log.info("Checking if train is present");
        try {
            return trainRepository
                    .findById(Long.parseLong(trainId))
                    .isPresent();
        } catch (NumberFormatException numberFormatException) {
            throw new IdNotAcceptableException(trainId);
        }
    }

    private void checkIfDatesAreAcceptable(BuyTicketDTO buyTicketDTO) {
        log.info("Checking if dates are acceptable");
        try {
            Year.parse(buyTicketDTO.getYear())
                    .atMonth(Integer.parseInt(buyTicketDTO.getMonth()))
                    .atDay(Integer.parseInt(buyTicketDTO.getDay()));
        } catch (DateTimeException dateTimeException) {
            throw new DateNotAcceptableException(buyTicketDTO.getYear() + " or " + buyTicketDTO.getMonth() + " or " + buyTicketDTO.getDay());
        }
    }

    @Transactional
    private String removeBalance(String customerId) {
        CustomerEntity customerEntity = customerRepository
                .findById(Long.parseLong(customerId))
                .orElseThrow(() -> new NoEntityFoundException("Customer ID: " + customerId));
        if (customerEntity.getBalance() < 1) {
            throw new NotEnoughBalanceException(customerId);
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
                .customerId(Long.parseLong(buyTicketDTO.getCustomerId()))
                .trainId(Long.parseLong(buyTicketDTO.getTrainId()))
                .purchaseTime(LocalDate.now())
                .validity(Year.parse(buyTicketDTO.getYear())
                        .atMonth(Integer.parseInt(buyTicketDTO.getMonth()))
                        .atDay(Integer.parseInt(buyTicketDTO.getDay())))
                .build();
        ticketEntity = ticketRepository.save(ticketEntity);
        String message = "Added new ticket to database with values " + ticketEntity.toString() + ". You can use your ID at inspection.";
        log.info(message);
        return message;
    }

}
