package com.fjhdream.truckbilling.service.billing;

import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;

public record TeamAndBilling(Team team, Billing billing) {
}
