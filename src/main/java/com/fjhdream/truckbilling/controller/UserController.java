package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.UserRequest;
import com.fjhdream.truckbilling.controller.entity.UserResponse;
import com.fjhdream.truckbilling.repository.UserRepository;
import com.fjhdream.truckbilling.repository.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author carota
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    void userCreate(@RequestBody @Valid UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setUserName(userRequest.getName());
        user.setAvatarUrl(user.getAvatarUrl());
        userRepository.save(user);
    }
}
