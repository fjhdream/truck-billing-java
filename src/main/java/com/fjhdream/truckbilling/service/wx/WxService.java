package com.fjhdream.truckbilling.service.wx;

import com.fjhdream.truckbilling.service.wx.entity.WxRequest;
import com.fjhdream.truckbilling.service.wx.entity.WxResponse;
import com.fjhdream.truckbilling.service.wx.exception.RestWxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author carota
 */
@Component
@Slf4j
public class WxService {

    @Value("${truck.app-id}")
    private String appId;

    @Value("${truck.app-secret}")
    private String secret;

    /**
     * ?appid={app_id}&secret={secret}&js_code={code}&grant_type=authorization_code
     */
    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";

    private final RestTemplate restTemplate = new RestTemplate();

    public WxResponse queryWxUserCode(WxRequest wxRequest) throws RestWxException {
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .queryParam("appid", appId)
                .queryParam("secret", secret)
                .queryParam("js_code", wxRequest.getCode())
                .build().toUri();

        WxResponse wxResponse;
        try {
            wxResponse = restTemplate.getForObject(uri, WxResponse.class);
        } catch (RestClientException restClientException) {
            log.error("Query wx exception.");
            throw new RestWxException(restClientException.getMessage());
        }
        if (wxResponse.getErrCode() != null && wxResponse.getErrCode() != 0) {
            throw new RestWxException(wxResponse.getErrCode(), wxResponse.getErrMsg());
        }
        return wxResponse;
    }
}
