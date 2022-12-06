package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCarResponse {

    @JsonProperty("car_id")
    private String carId;

    @JsonProperty("car_plate_number")
    private String carPlateNumber;
}
