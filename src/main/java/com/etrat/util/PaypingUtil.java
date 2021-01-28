package com.etrat.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import com.etrat.service.AuditEventService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(PaypingUtil.class);

    public String genrateCode(Integer amount, Long unicId) {
        PaypingCodeResquest paypingCodeResquest = new PaypingCodeResquest();
        paypingCodeResquest.setAmount(amount / 10);
        paypingCodeResquest.setClientRefId(unicId.toString());
        //        mustGetSecurityContex
        paypingCodeResquest.setPayerName("test");
        paypingCodeResquest.setReturnUrl(paypingRedirectUrl);
        HttpHeaders headers = new HttpHeaders();
        log.error("header: " + "bearer " + "329f76f572cc4d9033d71b6e76d27a43a888ccd2f8e2da1adf780ef52bb57287");
        log.error("payping redirect: " + paypingRedirectUrl);
        headers.set(AUTHORIZATION, "bearer " + "329f76f572cc4d9033d71b6e76d27a43a888ccd2f8e2da1adf780ef52bb57287");
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
        headers.set(AUTHORIZATION, "bearer " + "329f76f572cc4d9033d71b6e76d27a43a888ccd2f8e2da1adf780ef52bb57287");
        headers.set(CONTENT_TYPE, "application/json");
        HttpEntity<?> httpEntity = new HttpEntity(paypingCodeResquest, headers);
        VerifyResponse inventoryDto = restTemplate.postForEntity(baseUrl + "/pay/verify", httpEntity, VerifyResponse.class).getBody();
        return inventoryDto;
    }
}
