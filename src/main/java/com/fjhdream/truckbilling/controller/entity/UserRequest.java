package com.fjhdream.truckbilling.controller.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull
    private String id;

    @NotNull
    private String name;

    private String avatarUrl;
}
