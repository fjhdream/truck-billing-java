package com.fjhdream.truckbilling.controller.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRoleRequest {

    @NotBlank
    @JsonAlias("role_type")
    private String roleType;
}
