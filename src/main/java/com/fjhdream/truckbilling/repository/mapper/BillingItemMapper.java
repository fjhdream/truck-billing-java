package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.TeamBillingItemRequest;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.BillingItem;
import com.fjhdream.truckbilling.repository.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingItemMapper {

    BillingItemMapper INSTANCE = Mappers.getMapper(BillingItemMapper.class);

    @Mapping(target = "time", ignore = true)
    @Mapping(target = "id", ignore = true)
    BillingItem requestToBillingItem(TeamBillingItemRequest teamBillingItemRequest, Billing billing, Item item);
}
