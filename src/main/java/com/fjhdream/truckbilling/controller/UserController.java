package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.UserRequest;
import com.fjhdream.truckbilling.repository.UserRepository;
import com.fjhdream.truckbilling.repository.entity.User;
import com.fjhdream.truckbilling.repository.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    void userCreate(@Valid @RequestBody UserRequest userRequest) {
        User user = UserMapper.INSTANCE.userRequestToUserEntity(userRequest);
        userRepository.save(user);
    }
}
