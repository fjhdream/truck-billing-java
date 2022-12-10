package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamBillingIteamRequest {

    @JsonAlias("item_id")
    @NotBlank
    private String itemId;

    @DecimalMin("0.00")
    private BigDecimal cost;

    private String remark;
}
