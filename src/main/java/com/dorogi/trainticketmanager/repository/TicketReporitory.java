package com.dorogi.trainticketmanager.repository;

import com.dorogi.trainticketmanager.domain.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketReporitory extends JpaRepository<TicketEntity, Long> {

}
