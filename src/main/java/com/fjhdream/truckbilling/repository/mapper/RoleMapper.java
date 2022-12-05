package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.RoleResponse;
import com.fjhdream.truckbilling.repository.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "roleId")
    @Mapping(expression = "java(role.getRoleType().toString())", target = "roleType")
    RoleResponse roleToRoleResponse(Role role);

    @Mapping(source = "id", target = "roleId")
    @Mapping(expression = "java(role.getRoleType().toString())", target = "roleType")
    List<RoleResponse> roleListToRoleResponseList(List<Role> role);
}
