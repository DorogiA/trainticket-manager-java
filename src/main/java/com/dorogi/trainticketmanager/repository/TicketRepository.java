package com.dorogi.trainticketmanager.repository;

import com.dorogi.trainticketmanager.domain.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query("SELECT u FROM TicketEntity u WHERE u.customerId = ?1 and u.validity = ?2")
    Optional<TicketEntity> findValidTicket(Long customerId, LocalDate date);
}
