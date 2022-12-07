package com.fjhdream.truckbilling.service.user;

import com.fjhdream.truckbilling.repository.*;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class UserService {

    private final TeamCarRepository teamCarRepository;

    private final TeamRepository teamRepository;

    private final TeamDriverRepository teamDriverRepository;

    private final BillingRepository billingRepository;

    private final BillingItemRepository billingItemRepository;

    private final ItemRepository itemRepository;

    public UserService(TeamCarRepository teamCarRepository, TeamRepository teamRepository, TeamDriverRepository teamDriverRepository, BillingRepository billingRepository, BillingItemRepository billingItemRepository, ItemRepository itemRepository) {
        this.teamCarRepository = teamCarRepository;
        this.teamRepository = teamRepository;
        this.teamDriverRepository = teamDriverRepository;
        this.billingRepository = billingRepository;
        this.billingItemRepository = billingItemRepository;
        this.itemRepository = itemRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteTeam(@NotNull User user, @NotNull Team team) {
        Set<Team> userTeams = user.getTeams();
        for (Team userTeam : userTeams) {
            if (userTeam.equals(team)) {
                teamDriverRepository.deleteAll(userTeam.getTeamDrivers());
                teamCarRepository.deleteAll(userTeam.getTeamCars());
                Set<Billing> billings = userTeam.getBillings();
                for (Billing billing : billings) {
                    billingItemRepository.deleteAll(billing.getBillingItems());
                }
                billingRepository.deleteAll(billings);
                itemRepository.deleteAll(userTeam.getItems());
                teamRepository.delete(userTeam);
            }
        }
    }
}
