package com.fjhdream.truckbilling.controller.entity.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "User ID can not be empty!")
    private String id;

    @NotBlank(message = "User name can not be empty!")
    private String name;

    @JsonAlias("avatar_url")
    private String avatarUrl;
}
