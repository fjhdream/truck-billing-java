package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.UserTeamRequest;
import com.fjhdream.truckbilling.controller.entity.UserTeamResponse;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(target = "teamName", source = "userTeamRequest.name")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    Team userTeamRequestToTeam(User user, UserTeamRequest userTeamRequest);

    @Mapping(target = "teamId", source = "id")
    @Mapping(target = "teamName", source = "teamName")
    UserTeamResponse teamToUserTeamResponse(Team team);

    List<UserTeamResponse> teamSetToUserTeamResponseList(Set<Team> teamSet);
}
