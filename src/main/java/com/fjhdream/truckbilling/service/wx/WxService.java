package com.fjhdream.truckbilling.service.wx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fjhdream.truckbilling.service.wx.entity.WxRequest;
import com.fjhdream.truckbilling.service.wx.entity.WxResponse;
import com.fjhdream.truckbilling.service.wx.exception.RestWxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    /**
     * ?appid={app_id}&secret={secret}&js_code={code}&grant_type=authorization_code
     */
    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private final RestTemplate restTemplate = new RestTemplate();

    private final JsonMapper jsonMapper = new JsonMapper();

    @Value("${truck.app-id}")
    private String appId;
    @Value("${truck.app-secret}")
    private String secret;

    public WxResponse queryWxUserCode(WxRequest wxRequest) throws RestWxException {
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .queryParam("appid", appId)
                .queryParam("secret", secret)
                .queryParam("js_code", wxRequest.getCode())
                .queryParam("grant_type", "authorization_code")
                .build().toUri();

        WxResponse wxResponse = null;
        try {
            ResponseEntity<String> wxResponseResponseEntity = restTemplate.getForEntity(uri, String.class);
            String body = wxResponseResponseEntity.getBody();
            wxResponse = jsonMapper.readValue(body, WxResponse.class);
            log.info("Wx Response is {}", wxResponse);
        } catch (RestClientException restClientException) {
            log.error("Query wx exception.", restClientException);
            throw new RestWxException(restClientException.getMessage());
        } catch (JsonProcessingException e) {
            throw new RestWxException(e.getMessage());
        }
        if (wxResponse.getErrCode() != null && wxResponse.getErrCode() != 0) {
            throw new RestWxException(wxResponse.getErrCode(), wxResponse.getErrMsg());
        }
        return wxResponse;
    }
}
