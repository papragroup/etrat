package com.etrat.config;

import com.etrat.service.dto.HamiDTO;
import com.etrat.service.dto.HesabDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentTypeConfig {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public HesabDTO getZohaHesab() {
        return restTemplate.getForEntity("http://etrat-warapper/api/v1/payment-type", HesabDTO.class).getBody();
    }
}
