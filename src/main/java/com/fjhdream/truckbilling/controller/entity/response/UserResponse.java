package com.fjhdream.truckbilling.controller.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String id;

    private String name;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private List<RoleResponse> roles;
}
