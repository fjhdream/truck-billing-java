package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.RoleResponse;
import com.fjhdream.truckbilling.controller.entity.UserRoleRequest;
import com.fjhdream.truckbilling.repository.RoleRepository;
import com.fjhdream.truckbilling.repository.entity.Role;
import com.fjhdream.truckbilling.repository.entity.User;
import com.fjhdream.truckbilling.repository.mapper.RoleMapper;
import com.fjhdream.truckbilling.service.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class UserRoleController {


    private final UserService userService;

    private final RoleRepository roleRepository;


    public UserRoleController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/user/role/{user_id}")
    @ResponseStatus(HttpStatus.CREATED)
    void addUserRole(@PathVariable("user_id") String userId, @Valid @RequestBody UserRoleRequest userRoleRequest) {
        User user = userService.checkUser(userId);
        Role role = RoleMapper.INSTANCE.requestToRole(user, userRoleRequest);
        roleRepository.save(role);
    }

    @GetMapping("/user/role/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    List<RoleResponse> getUserRole(@PathVariable("user_id") String userId) {
        User user = userService.checkUser(userId);
        return RoleMapper.INSTANCE.roleSetToRoleResponseList(user.getRoles());
    }

    @GetMapping("/user/role/{user_id}/{role_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserRole(@PathVariable("user_id") String userId, @PathVariable("role_id") String role_id) {
        User user = userService.checkUser(userId);
        Optional<Role> roleOptional = roleRepository.findById(UUID.fromString(role_id));
        if (roleOptional.isEmpty()) {
            log.error("User {} is not exist.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }
        Role role = roleOptional.get();
        for (Role userRole : user.getRoles()) {
            if (userRole.equals(role)) {
                roleRepository.delete(role);
            }
        }
    }
}
