package com.fjhdream.truckbilling.controller.mapper;

import com.fjhdream.truckbilling.controller.entity.request.UserWxRequest;
import com.fjhdream.truckbilling.controller.entity.response.UserWxResponse;
import com.fjhdream.truckbilling.service.wx.entity.WxRequest;
import com.fjhdream.truckbilling.service.wx.entity.WxResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWxMapper {
    UserWxMapper INSTANCE = Mappers.getMapper(UserWxMapper.class);

    WxRequest wxRequestFromUserWxRequest(UserWxRequest userWxRequest);

    @Mapping(source = "openid", target = "code")
    UserWxResponse wxResponseToUserWxResponse(WxResponse wxResponse);
}
