package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
