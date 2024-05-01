package com.dorogi.trainticketmanager.repository;

import com.dorogi.trainticketmanager.domain.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long> {

}
