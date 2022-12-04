package com.fjhdream.truckbilling.service.wx.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author carota
 */
@Data
public class WxResponse {

    private String openid;

    @JsonAlias("session_key")
    private String sessionKey;

    @JsonAlias("unionid")
    private String unionId;

    @JsonAlias("errcode")
    private Integer errCode;

    @JsonAlias("errmsg")
    private String errMsg;
}
