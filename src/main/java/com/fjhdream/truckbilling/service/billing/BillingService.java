package com.fjhdream.truckbilling.service.billing;

import com.fjhdream.truckbilling.repository.BillingRepository;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
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

    public BillingService(TeamRepository teamRepository, BillingRepository billingRepository) {
        this.teamRepository = teamRepository;
        this.billingRepository = billingRepository;
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

    public Team authTeam(@NotBlank String teamId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }
        return teamOptional.get();
    }
}
