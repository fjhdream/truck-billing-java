package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.RoleResponse;
import com.fjhdream.truckbilling.controller.entity.UserRoleRequest;
import com.fjhdream.truckbilling.repository.entity.Role;
import com.fjhdream.truckbilling.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "roleId")
    @Mapping(expression = "java(role.getRoleType().toString())", target = "roleType")
    RoleResponse roleToRoleResponse(Role role);

    List<RoleResponse> roleListToRoleResponseList(List<Role> role);

    
    List<RoleResponse> roleSetToRoleResponseList(Set<Role> role);

    @Mapping(target = "id", ignore = true)
    Role requestToRole(User user, UserRoleRequest userRoleRequest);
}
