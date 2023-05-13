package com.fjhdream.truckbilling.controller;

import com.fjhdream.truckbilling.controller.entity.request.TeamBillingItemRequest;
import com.fjhdream.truckbilling.repository.BillingItemRepository;
import com.fjhdream.truckbilling.repository.entity.BillingItem;
import com.fjhdream.truckbilling.repository.mapper.BillingItemMapper;
import com.fjhdream.truckbilling.service.billing.BillingService;
import com.fjhdream.truckbilling.service.billing.TeamAndBilling;
import com.fjhdream.truckbilling.service.billing.TeamAndBillingAndItem;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

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
                            @Valid @RequestBody TeamBillingItemRequest teamBillingItemRequest) {
        TeamAndBillingAndItem teamAndBillingAndItem = billingService.authTeamAndBillingAddItem(teamId, billingId, teamBillingItemRequest.getItemId());
        BillingItem billingItem = BillingItemMapper.INSTANCE.requestToBillingItem(teamBillingItemRequest, teamAndBillingAndItem.billing(), teamAndBillingAndItem.item());
        billingItemRepository.save(billingItem);
    }

    @PutMapping("/team/{team_id}/billing/{billing_id}/item/{billing_item_id}")
    @ResponseStatus(HttpStatus.CREATED)
    void teamBillingUpdateItem(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId,
                               @PathVariable("billing_item_id") String billingItemId, @Valid @RequestBody TeamBillingItemRequest teamBillingItemRequest) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);
        Optional<BillingItem> billingItemOptional = billingItemRepository.findById(UUID.fromString(billingItemId));
        if (billingItemOptional.isEmpty()) {
            log.error("Billing item {} is empty.", billingItemId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing item is not exist.");
        }

        BillingItem billingItem = billingItemOptional.get();
        if (!billingItem.getBilling().equals(teamAndBilling.billing())) {
            log.error("Billing item {} is not belong to billing {}.", billingItemId, billingId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing item is not belong to billing.");
        }

        if (teamBillingItemRequest.getCost() != null) {
            billingItem.setCost(teamBillingItemRequest.getCost());
        }

        if (teamBillingItemRequest.getRemark() != null) {
            billingItem.setRemark(teamBillingItemRequest.getRemark());
        }

        billingItemRepository.save(billingItem);
    }

    @DeleteMapping("/team/{team_id}/billing/{billing_id}/item/{billing_item_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void teamBillingDeleteItem(@PathVariable("team_id") String teamId, @PathVariable("billing_id") String billingId,
                               @PathVariable("billing_item_id") String billingItemId, @Valid @RequestBody TeamBillingItemRequest teamBillingItemRequest) {
        TeamAndBilling teamAndBilling = billingService.authTeamAndBilling(teamId, billingId);
        Optional<BillingItem> billingItemOptional = billingItemRepository.findById(UUID.fromString(billingItemId));
        if (billingItemOptional.isEmpty()) {
            log.error("Billing item {} is empty.", billingItemId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing item is not exist.");
        }

        BillingItem billingItem = billingItemOptional.get();
        if (!billingItem.getBilling().equals(teamAndBilling.billing())) {
            log.error("Billing item {} is not belong to billing {}.", billingItemId, billingId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Billing item is not belong to billing.");
        }

        billingItemRepository.delete(billingItem);
    }

}
