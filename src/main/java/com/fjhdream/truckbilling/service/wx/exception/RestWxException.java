package com.fjhdream.truckbilling.service.wx.exception;

import lombok.Getter;

/**
 * @author carota
 */
@Getter
public class RestWxException extends Exception {

    private Integer code;

    private final String errMsg;

    public RestWxException(String msg) {
        super(msg);
        this.errMsg = msg;
    }

    public RestWxException(Integer code, String errorMsg) {
        super(errorMsg);
        this.code = code;
        this.errMsg = errorMsg;
    }
}
