package com.fjhdream.truckbilling.controller.entity;

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
public class UserTeamRequest {

    @NotBlank
    private String name;
}
