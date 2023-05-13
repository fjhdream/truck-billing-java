package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.request.TeamBillingRequest;
import com.fjhdream.truckbilling.controller.entity.response.TeamBillingResponse;
import com.fjhdream.truckbilling.repository.BillingRepository;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;
import com.fjhdream.truckbilling.repository.enums.BillingStatusEnum;
import com.fjhdream.truckbilling.repository.mapper.BillingMapper;
import com.fjhdream.truckbilling.service.billing.BillingService;
import com.fjhdream.truckbilling.service.billing.TeamAndBilling;
import com.fjhdream.truckbilling.service.billing.TeamAndTeamCar;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
public class BillingController {

    private final BillingRepository billingRepository;

    private final BillingService billingService;

    public BillingController(BillingRepository billingRepository, BillingService billingService) {
        this.billingRepository = billingRepository;
        this.billingService = billingService;
    }

    @GetMapping("/team/{team_id}/billing")
    @ResponseStatus(HttpStatus.OK)
    List<TeamBillingResponse> teamGetBilling(@PathVariable("team_id") String teamId) {
        Team team = billingService.authTeam(teamId);
        return BillingMapper.INSTANCE.biilingSetToTeamBillingResponseList(team.getBillings());

    }

    @PostMapping("/team/{team_id}/billing")
    @ResponseStatus(HttpStatus.CREATED)
    void teamCreateBilling(@PathVariable("team_id") String teamId, @Valid @RequestBody TeamBillingRequest teamBillingRequest) {
        TeamAndTeamCar teamAndTeamCar = billingService.authTeamAndTeamCar(teamId, teamBillingRequest.getTeamCarId());
        Team team = teamAndTeamCar.team();
        TeamCar teamCar = teamAndTeamCar.teamCar();
        Billing billing = BillingMapper.INSTANCE.teamBillingRequestToBilling(team, teamCar, teamBillingRequest);
        billingRepository.save(billing);

    }

    @PutMapping("/team/{team_id}/billing/{billing_id}")
    @ResponseStatus(HttpStatus.OK)
    void teamUpdateBilling(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId, @Valid @RequestBody TeamBillingRequest teamBillingRequest) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);
        Team team = teamAndBilling.team();
        Billing billing = teamAndBilling.billing();

        Set<Billing> teamBillings = team.getBillings();
        if (!teamBillings.contains(billing)) {
            log.error("Billing{} is not belong to team {}.", billingId, teamId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Billing is not belong to team.");
        }

        billing.setName(teamBillingRequest.getName());

        billingRepository.save(billing);
    }

    @PutMapping("/team/{team_id}/billing/{billing_id}/start")
    @ResponseStatus(HttpStatus.OK)
    void teamStartBilling(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);

        Billing billing = teamAndBilling.billing();
        billing.setStatus(BillingStatusEnum.STARTED);
        billing.setStartTime(Instant.now());
        billingRepository.save(billing);
    }

    @PutMapping("/team/{team_id}/billing/{billing_id}/end")
    @ResponseStatus(HttpStatus.OK)
    void teamEndBilling(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);

        Billing billing = teamAndBilling.billing();
        billing.setStatus(BillingStatusEnum.ENDED);
        billing.setEndTime(Instant.now());
        billingRepository.save(billing);
    }

    @PutMapping("/team/{team_id}/billing/{billing_id}/close")
    @ResponseStatus(HttpStatus.OK)
    void teamCloseBilling(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);

        Billing billing = teamAndBilling.billing();
        billing.setStatus(BillingStatusEnum.CLOSED);
        billingRepository.save(billing);
    }


}
