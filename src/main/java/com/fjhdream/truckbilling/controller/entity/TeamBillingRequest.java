package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author carota
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamBillingRequest {

    @JsonAlias("name")
    @NotBlank
    private String name;

    @JsonAlias("team_car_id")
    @NotBlank
    private String teamCarId;

    private String commodity;

    private String startLocation;

    private String endLocation;

    @DecimalMin("0")
    private BigDecimal startMoney;

    @DecimalMin("0")
    private BigDecimal endMoney;
}
