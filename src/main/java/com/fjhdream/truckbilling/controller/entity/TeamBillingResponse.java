package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamBillingResponse {

    @JsonProperty("billing_id")
    private String billingId;

    @JsonProperty("billing_name")
    private String billingName;

    @JsonProperty("billing_status")
    private String billingStatus;
}
