package com.fjhdream.truckbilling.controller.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTeamResponse {

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("team_name")
    private String teamName;
}
