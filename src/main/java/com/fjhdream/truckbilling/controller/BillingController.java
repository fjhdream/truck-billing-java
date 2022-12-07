package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.TeamBillingRequest;
import com.fjhdream.truckbilling.controller.entity.TeamBillingResponse;
import com.fjhdream.truckbilling.repository.BillingRepository;
import com.fjhdream.truckbilling.repository.TeamRepository;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.mapper.BillingMapper;
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
public class BillingController {

    private final TeamRepository teamRepository;

    private final BillingRepository billingRepository;

    public BillingController(TeamRepository teamRepository, BillingRepository billingRepository) {
        this.teamRepository = teamRepository;
        this.billingRepository = billingRepository;
    }

    @GetMapping("/team/{team_id}/billing")
    @ResponseStatus(HttpStatus.OK)
    List<TeamBillingResponse> teamGetBilling(@PathVariable("team_id") String teamId) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        } else {
            Team team = teamOptional.get();
            return BillingMapper.INSTANCE.biilingSetToTeamBillingResponseList(team.getBillings());
        }
    }

    @PostMapping("/team/{team_id}/billing")
    @ResponseStatus(HttpStatus.CREATED)
    void teamCreateBilling(@PathVariable("team_id") String teamId, @RequestBody TeamBillingRequest teamBillingRequest) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        } else {
            Team team = teamOptional.get();
            Billing billing = BillingMapper.INSTANCE.teamBillingRequestToBilling(team, teamBillingRequest);
            billingRepository.save(billing);
        }
    }

    @PutMapping("/team/{team_id}/billing/{billing_id}")
    @ResponseStatus(HttpStatus.OK)
    void teamUpdateBilling(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId,
                           @Valid @RequestBody TeamBillingRequest teamBillingRequest) {
        Optional<Team> teamOptional = teamRepository.findById(UUID.fromString(teamId));
        if (teamOptional.isEmpty()) {
            log.error("Team is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is not exist.");
        }

        Optional<Billing> billingOptional = billingRepository.findById(UUID.fromString(billingId));

        if (billingOptional.isEmpty()) {
            log.error("Billing is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing is not exist.");
        }

        Team team = teamOptional.get();
        Billing billing = billingOptional.get();

        Set<Billing> teamBillings = team.getBillings();
        if (!teamBillings.contains(billing)) {
            log.error("Billing{} is not belong to team {}.", billingId, teamId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Billing is not belong to team.");
        }

        billing.setName(teamBillingRequest.getName());

        billingRepository.save(billing);
    }
}
