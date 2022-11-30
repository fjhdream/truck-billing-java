package com.fjhdream.truckbilling.repository.mapper;

import com.fjhdream.truckbilling.controller.entity.UserRequest;
import com.fjhdream.truckbilling.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name", target = "userName")
    User userRequestToUserEntity (UserRequest userRequest);
}
