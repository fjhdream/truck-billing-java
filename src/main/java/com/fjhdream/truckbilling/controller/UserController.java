package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.UserRequest;
import com.fjhdream.truckbilling.controller.entity.UserResponse;
import com.fjhdream.truckbilling.controller.entity.UserWxRequest;
import com.fjhdream.truckbilling.controller.entity.UserWxResponse;
import com.fjhdream.truckbilling.controller.mapper.UserWxMapper;
import com.fjhdream.truckbilling.repository.UserRepository;
import com.fjhdream.truckbilling.repository.entity.User;
import com.fjhdream.truckbilling.repository.mapper.UserMapper;
import com.fjhdream.truckbilling.service.wx.WxService;
import com.fjhdream.truckbilling.service.wx.entity.WxResponse;
import com.fjhdream.truckbilling.service.wx.exception.RestWxException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author carota
 */
@Slf4j
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final WxService wxService;

    public UserController(UserRepository userRepository, WxService wxService) {
        this.userRepository = userRepository;
        this.wxService = wxService;
    }


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    void userCreate(@Valid @RequestBody UserRequest userRequest) {
        User user = UserMapper.INSTANCE.userRequestToUserEntity(userRequest);
        userRepository.save(user);
    }

    @PostMapping("/user/login")
    @ResponseStatus(HttpStatus.OK)
    UserWxResponse userWxLogin(@Valid @RequestBody UserWxRequest userRequest) {
        UserWxMapper userWxMapper = UserWxMapper.INSTANCE;
        try {
            WxResponse wxResponse = wxService.queryWxUserCode(userWxMapper.wxRequestFromUserWxRequest(userRequest));
            return userWxMapper.wxResponseToUserWxResponse(wxResponse);
        } catch (RestWxException e) {
            log.info("RestWxException is {}", e.getErrMsg());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Query wx login error.");
        }
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse userQuery(@PathVariable String id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new UserResponse();
        } else {
            return UserMapper.INSTANCE.userToUserResponse(user.get());
        }
    }
}
