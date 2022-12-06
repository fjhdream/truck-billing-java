package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.TeamCarRequest;
import com.fjhdream.truckbilling.controller.entity.TeamCarResponse;
import com.fjhdream.truckbilling.repository.TeamCarRepository;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;
import com.fjhdream.truckbilling.repository.mapper.TeamCarMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class TeamCarController {

    private final TeamRepository teamRepository;

    private final TeamCarRepository teamCarRepository;

    public TeamCarController(TeamRepository teamRepository, TeamCarRepository teamCarRepository) {
        this.teamRepository = teamRepository;
        this.teamCarRepository = teamCarRepository;
    }

    @PostMapping("/team/{team_id}/car")
    @ResponseStatus(HttpStatus.CREATED)
    void teamCreateCar(@PathVariable("team_id") String teamId, @RequestBody TeamCarRequest teamCarRequest) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        } else {
            Team team = teamOptional.get();
            TeamCar teamCar = TeamCarMapper.INSTANCE.teamCarFromTeamCarRequestAndTeam(teamCarRequest, team);
            teamCarRepository.save(teamCar);
        }
    }

    @GetMapping("/team/{team_id}/car")
    @ResponseStatus(HttpStatus.OK)
    List<TeamCarResponse> teamGetCar(@PathVariable("team_id") String teamId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        } else {
            Team team = teamOptional.get();
            return TeamCarMapper.INSTANCE.teamCarListToTeamCarResponseList(team.getTeamCars());
        }
    }

    @DeleteMapping("/team/{team_id}/car/{car_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void teamDeleteCar(@PathVariable("team_id") String teamId, @PathVariable("car_id") String carId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        Optional<TeamCar> teamCarOptional = teamCarRepository.findById(UUID.fromString(carId));
        if (teamCarOptional.isEmpty()) {
            log.warn("Car {} which belong to team {} is empty", carId, teamId);
            return;
        }
        if (teamOptional.isEmpty()) {
            log.error("Team is empty;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }

        Team team = teamOptional.get();
        TeamCar teamCar = teamCarOptional.get();
        if (team.getTeamCars().contains(teamCar)) {
            teamCarRepository.delete(teamCar);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The car is not belong to this team");
        }
    }
}
