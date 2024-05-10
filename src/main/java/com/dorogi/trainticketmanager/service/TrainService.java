package com.dorogi.trainticketmanager.service;

import com.dorogi.trainticketmanager.domain.dto.TrainDTO;
import com.dorogi.trainticketmanager.domain.entity.TrainEntity;
import com.dorogi.trainticketmanager.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;

    public List<TrainDTO> findTrains() {
        List<TrainEntity> foundTrains = trainRepository.findAll();
        List<TrainDTO> mappedTrains = mapTrainEntityToDTO(foundTrains);
        return mappedTrains;
    }

    private List<TrainDTO> mapTrainEntityToDTO(List<TrainEntity> trainEntities) {
        return trainEntities.stream()
                .map(trainEntity -> trainToDTO(trainEntity))
                .collect(Collectors.toList());
    }

    private TrainDTO trainToDTO(TrainEntity trainEntity) {
        return TrainDTO
                .builder()
                .id(trainEntity.getId())
                .trainName(trainEntity.getTrainName())
                .build();
    }

}
