package com.fjhdream.truckbilling.service.billing;

import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Item;
import com.fjhdream.truckbilling.repository.entity.Team;

public record TeamAndBillingAndItem(Team team, Billing billing, Item item) {
}
