package com.fjhdream.truckbilling.controller.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleResponse {

    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("role_type")
    private String roleType;
}
