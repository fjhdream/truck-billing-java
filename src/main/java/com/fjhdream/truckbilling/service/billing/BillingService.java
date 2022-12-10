package com.fjhdream.truckbilling.service.billing;

import com.fjhdream.truckbilling.repository.BillingRepository;
import com.fjhdream.truckbilling.repository.TeamCarRepository;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;
import com.fjhdream.truckbilling.repository.enums.UseStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class BillingService {

    private final TeamRepository teamRepository;

    private final BillingRepository billingRepository;

    private final TeamCarRepository teamCarRepository;

    public BillingService(TeamRepository teamRepository, BillingRepository billingRepository, TeamCarRepository teamCarRepository) {
        this.teamRepository = teamRepository;
        this.billingRepository = billingRepository;
        this.teamCarRepository = teamCarRepository;
    }

    public TeamAndBilling authTeamAndBilling(@NotBlank String teamId, @NotBlank String billingId) {
        Team team = authTeam(teamId);

        Optional<Billing> billingOptional = billingRepository.findById(UUID.fromString(billingId));

        if (billingOptional.isEmpty()) {
            log.error("Billing is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing is not exist.");
        }

        return new TeamAndBilling(team, billingOptional.get());
    }

    public TeamAndTeamCar authTeamAndTeamCar(@NotBlank String teamId, @NotBlank String teamCarId) {
        Team team = authTeam(teamId);

        Optional<TeamCar> teamCarOptional = teamCarRepository.findById(UUID.fromString(teamCarId));
        if (teamCarOptional.isEmpty()) {
            log.error("Billing is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing is not exist.");
        }
        TeamCar teamCar = teamCarOptional.get();
        if (teamCar.getStatus() == UseStatusEnum.DELETED) {
            log.error("TeamCar {} is deleted.", teamCarId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TeamCar is deleted.");
        }
        return new TeamAndTeamCar(team, teamCar);
    }

    public Team authTeam(@NotBlank String teamId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }
        return teamOptional.get();
    }
}
