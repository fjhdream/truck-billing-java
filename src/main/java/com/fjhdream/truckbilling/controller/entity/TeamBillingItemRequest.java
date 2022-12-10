package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamBillingItemRequest {

    @JsonAlias("item_id")
    @Nullable
    private String itemId;

    @DecimalMin("0.00")
    private BigDecimal cost;

    private String remark;
}
