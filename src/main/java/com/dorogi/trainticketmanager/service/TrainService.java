package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.TrainDTO;
import com.dorogi.trainticketmanager.domain.entity.TrainEntity;
import com.dorogi.trainticketmanager.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;

    public List<TrainEntity> findTrains() {
        List<TrainEntity> foundTrains = trainRepository.findAll();
        return foundTrains;
    }

}
