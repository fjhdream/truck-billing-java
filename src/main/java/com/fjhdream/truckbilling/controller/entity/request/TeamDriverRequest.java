package com.fjhdream.truckbilling.controller.entity.request;

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
public class TeamDriverRequest {

    @NotBlank
    @JsonAlias("user_id")
    private String userId;
}
