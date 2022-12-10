package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.TeamBillingIteamRequest;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.BillingItem;
import com.fjhdream.truckbilling.repository.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingItemMapper {

    BillingItemMapper INSTANCE = Mappers.getMapper(BillingItemMapper.class);

    @Mapping(target = "id", ignore = true)
    BillingItem requestToBillingItem(TeamBillingIteamRequest teamBillingIteamRequest, Billing billing, Item item);
}
