package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.request.UserTeamRequest;
import com.fjhdream.truckbilling.controller.entity.response.UserTeamResponse;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.UserRepository;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.User;
import com.fjhdream.truckbilling.repository.mapper.TeamMapper;
import com.fjhdream.truckbilling.service.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author carota
 */
@RestController
@Slf4j
public class UserTeamController {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    private final UserService userService;

    public UserTeamController(UserRepository userRepository, TeamRepository teamRepository, UserService userService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.userService = userService;
    }


    @PostMapping("/user/{user_id}/team")
    @ResponseStatus(HttpStatus.CREATED)
    void userCreateTeam(@PathVariable("user_id") String userId, @Valid @RequestBody UserTeamRequest userTeamRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }

        User user = userOptional.get();
        Team team = TeamMapper.INSTANCE.userTeamRequestToTeam(user, userTeamRequest);
        teamRepository.save(team);
    }

    @GetMapping("/user/{user_id}/team")
    @ResponseStatus(HttpStatus.OK)
    List<UserTeamResponse> userGetTeam(@PathVariable("user_id") String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }
        User user = userOptional.get();
        return TeamMapper.INSTANCE.teamSetToUserTeamResponseList(user.getTeams());
    }

    @DeleteMapping("/user/{user_id}/team/{team_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void userDeleteTeam(@PathVariable("team_id") String teamId, @PathVariable("user_id") String userId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.warn("User {} which belong to team {} is empty", userId, teamId);
            return;
        }
        if (teamOptional.isEmpty()) {
            log.error("Team is empty;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }

        Team team = teamOptional.get();
        User user = userOptional.get();
        userService.deleteTeam(user, team);
    }

    @PutMapping("/user/{user_id}/team/{team_id}")
    @ResponseStatus(HttpStatus.OK)
    void userUpdateTeam(@PathVariable("user_id") String userId, @PathVariable("team_id") String teamId, @Valid @RequestBody UserTeamRequest userTeamRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }

        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }

        Team team = teamOptional.get();

        User user = userOptional.get();
        Set<Team> userTeams = user.getTeams();
        if (!userTeams.contains(team)) {
            log.error("Team {} is not belong to user {}", teamId, userId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Team is not exist.");
        }
        team.setTeamName(userTeamRequest.getName());
        teamRepository.save(team);
    }
}
