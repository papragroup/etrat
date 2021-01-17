package com.etrat.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaypingUtil {
    @Value("${payping-redirect-url}")
    private String paypingRedirectUrl;

    @Value("${payping-token}")
    private String paypingToken;

    @Value("${payping-base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String genrateCode(Integer amount, Long unicId) {
        PaypingCodeResquest paypingCodeResquest = new PaypingCodeResquest();
        paypingCodeResquest.setAmount(amount / 10);
        paypingCodeResquest.setClientRefId(unicId.toString());
        //        mustGetSecurityContex
        paypingCodeResquest.setPayerName("test");
        paypingCodeResquest.setReturnUrl(paypingRedirectUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, "bearer " + paypingToken);
        headers.set(CONTENT_TYPE, "application/json");
        HttpEntity<?> httpEntity = new HttpEntity(paypingCodeResquest, headers);
        PaypingCodeResponse inventoryDto = restTemplate.postForEntity(baseUrl + "/pay", httpEntity, PaypingCodeResponse.class).getBody();
        return inventoryDto.getCode();
    }

    public VerifyResponse verify(String refid, Integer amount) {
        VerifyRequest paypingCodeResquest = new VerifyRequest();
        paypingCodeResquest.setAmount(amount);
        paypingCodeResquest.setRefId(refid);
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, "bearer " + paypingToken);
        headers.set(CONTENT_TYPE, "application/json");
        HttpEntity<?> httpEntity = new HttpEntity(paypingCodeResquest, headers);
        VerifyResponse inventoryDto = restTemplate.postForEntity(baseUrl + "/pay/verify", httpEntity, VerifyResponse.class).getBody();
        return inventoryDto;
    }
}
