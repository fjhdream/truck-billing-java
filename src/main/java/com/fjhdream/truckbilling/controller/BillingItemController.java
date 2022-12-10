package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.TeamBillingIteamRequest;
import com.fjhdream.truckbilling.repository.BillingItemRepository;
import com.fjhdream.truckbilling.repository.entity.BillingItem;
import com.fjhdream.truckbilling.repository.mapper.BillingItemMapper;
import com.fjhdream.truckbilling.service.billing.BillingService;
import com.fjhdream.truckbilling.service.billing.TeamAndBillingAndItem;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BillingItemController {

    private final BillingService billingService;

    private final BillingItemRepository billingItemRepository;

    public BillingItemController(BillingService billingService, BillingItemRepository billingItemRepository) {
        this.billingService = billingService;
        this.billingItemRepository = billingItemRepository;
    }

    @PostMapping("/team/{team_id}/billing/{billing_id}/item")
    @ResponseStatus(HttpStatus.CREATED)
    void teamBillingAddItem(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId,
                            @Valid @RequestBody TeamBillingIteamRequest teamBillingIteamRequest) {
        TeamAndBillingAndItem teamAndBillingAndItem = billingService.authTeamAndBillingAddItem(teamId, billingId, teamBillingIteamRequest.getItemId());
        BillingItem billingItem = BillingItemMapper.INSTANCE.requestToBillingItem(teamBillingIteamRequest, teamAndBillingAndItem.billing(), teamAndBillingAndItem.item());
        billingItemRepository.save(billingItem);
    }


}
