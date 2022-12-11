package com.fjhdream.truckbilling.service.user;

import com.fjhdream.truckbilling.repository.*;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class UserService {

    private final TeamCarRepository teamCarRepository;

    private final TeamRepository teamRepository;

    private final TeamDriverRepository teamDriverRepository;

    private final BillingRepository billingRepository;

    private final BillingItemRepository billingItemRepository;

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public UserService(TeamCarRepository teamCarRepository, TeamRepository teamRepository, TeamDriverRepository teamDriverRepository, BillingRepository billingRepository, BillingItemRepository billingItemRepository, ItemRepository itemRepository,
                       UserRepository userRepository) {
        this.teamCarRepository = teamCarRepository;
        this.teamRepository = teamRepository;
        this.teamDriverRepository = teamDriverRepository;
        this.billingRepository = billingRepository;
        this.billingItemRepository = billingItemRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public User checkUser(@NotNull String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User {} is not exist.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist.");
        }
        return userOptional.get();
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
