package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.TeamCarRequest;
import com.fjhdream.truckbilling.controller.entity.TeamCarResponse;
import com.fjhdream.truckbilling.repository.entity.Team;
import com.fjhdream.truckbilling.repository.entity.TeamCar;
import com.fjhdream.truckbilling.repository.enums.UseStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author carota
 */
@Mapper(imports = {UseStatusEnum.class})
public interface TeamCarMapper {
    TeamCarMapper INSTANCE = Mappers.getMapper(TeamCarMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(UseStatusEnum.INUSE)")
    TeamCar teamCarFromTeamCarRequestAndTeam(TeamCarRequest teamCarRequest, Team team);

    @Mapping(target = "carId", source = "id")
    TeamCarResponse teamCarToTeamCarResponse(TeamCar teamCar);

    @Mapping(target = "carId", source = "id")
    List<TeamCarResponse> teamCarListToTeamCarResponseList(Set<TeamCar> teamCarSet);
}
