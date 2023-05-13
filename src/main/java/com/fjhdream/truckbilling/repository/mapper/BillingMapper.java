package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.request.TeamBillingRequest;
import com.fjhdream.truckbilling.controller.entity.response.TeamBillingResponse;
import com.fjhdream.truckbilling.repository.entity.Billing;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;
import com.fjhdream.truckbilling.repository.enums.BillingStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(imports = {BillingStatusEnum.class})
public interface BillingMapper {

    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "teamBillingRequest.name")
    @Mapping(target = "status", expression = "java(BillingStatusEnum.INITIALIZED)")
    Billing teamBillingRequestToBilling(Team team, TeamCar teamCar, TeamBillingRequest teamBillingRequest);

    @Mapping(target = "billingName", source = "name")
    @Mapping(target = "billingId", source = "id")
    @Mapping(target = "billingStatus", source = "status")
    TeamBillingResponse billingToTeamBillingResponse(Billing billing);


    List<TeamBillingResponse> biilingSetToTeamBillingResponseList(Set<Billing> billingSet);
}
