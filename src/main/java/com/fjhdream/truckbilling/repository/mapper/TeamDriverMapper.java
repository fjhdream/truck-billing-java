package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.response.TeamDriverResponse;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamDriver;
import com.fjhdream.truckbilling.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface TeamDriverMapper {

    TeamDriverMapper INSTANCE = Mappers.getMapper(TeamDriverMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "team", source = "team")
    TeamDriver teamDriverRequestToTeamDriver(User user, Team team);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.userName")
    TeamDriverResponse teamDriverToTeamDriverResponse(TeamDriver teamDriver);

    List<TeamDriverResponse> teamDriverSetToTeamDriverResponseList(Set<TeamDriver> teamDriverSet);
}
