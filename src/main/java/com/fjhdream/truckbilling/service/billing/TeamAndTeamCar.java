package com.fjhdream.truckbilling.service.billing;

import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;

public record TeamAndTeamCar(Team team, TeamCar teamCar) {
}
