package com.fjhdream.truckbilling.controller.entity.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author carota
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCarRequest {

    @JsonAlias("car_plate_number")
    private String carPlateNumber;
}
