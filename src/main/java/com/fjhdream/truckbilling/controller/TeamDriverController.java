package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.request.TeamDriverRequest;
import com.fjhdream.truckbilling.controller.entity.response.TeamDriverResponse;
import com.fjhdream.truckbilling.repository.TeamDriverRepository;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.UserRepository;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamDriver;
import com.fjhdream.truckbilling.repository.entity.User;
import com.fjhdream.truckbilling.repository.mapper.TeamDriverMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@Slf4j
public class TeamDriverController {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    private final TeamDriverRepository teamDriverRepository;

    public TeamDriverController(UserRepository userRepository, TeamRepository teamRepository, TeamDriverRepository teamDriverRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.teamDriverRepository = teamDriverRepository;
    }


    @PostMapping("/team/{team_id}/driver")
    @ResponseStatus(HttpStatus.CREATED)
    void teamCreateDriver(@PathVariable("team_id") String teamId, @Valid @RequestBody TeamDriverRequest teamDriverRequest) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }

        Optional<User> userOptional = userRepository.findById(teamDriverRequest.getUserId());
        if (userOptional.isEmpty()) {
            log.error("User is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }

        Team team = teamOptional.get();
        User user = userOptional.get();
        TeamDriver teamDriver = TeamDriverMapper.INSTANCE.teamDriverRequestToTeamDriver(user, team);
        teamDriverRepository.save(teamDriver);
    }

    @GetMapping("/team/{team_id}/driver")
    @ResponseStatus(HttpStatus.OK)
    List<TeamDriverResponse> teamGetDriver(@PathVariable("team_id") String teamId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        } else {
            Team team = teamOptional.get();
            return TeamDriverMapper.INSTANCE.teamDriverSetToTeamDriverResponseList(team.getTeamDrivers());
        }
    }

    @DeleteMapping("/team/{team_id}/driver/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void teamDeleteDriver(@PathVariable("team_id") String teamId, @PathVariable("user_id") String userId) {
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
        Set<TeamDriver> teamDrivers = team.getTeamDrivers();
        for (TeamDriver teamDriver : teamDrivers) {
            if (teamDriver.getUser().equals(user)) {
                teamDriverRepository.delete(teamDriver);
            }
        }
    }
}
