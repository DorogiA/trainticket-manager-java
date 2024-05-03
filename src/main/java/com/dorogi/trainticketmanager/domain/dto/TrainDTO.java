package com.dorogi.trainticketmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainDTO {
    private String id;
    private String trainName;
}
