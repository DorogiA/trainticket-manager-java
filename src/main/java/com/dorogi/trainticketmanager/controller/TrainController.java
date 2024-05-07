package com.dorogi.trainticketmanager.controller;

import com.dorogi.trainticketmanager.domain.dto.TrainDTO;
import com.dorogi.trainticketmanager.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/train/get")
    public ResponseEntity<List<TrainDTO>> searchTrains() {
        List<TrainDTO> foundTrains = trainService.findTrains();
        return ResponseEntity.status(200).body(foundTrains);
    }
}
