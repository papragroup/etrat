package com.etrat.config;

import com.etrat.domain.TransactionType;
import com.etrat.repository.TransactionTypeRepository;
import com.etrat.service.dto.HamiDTO;
import com.etrat.service.dto.HesabDTO;
import com.etrat.service.dto.ZohaHesab;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentTypeConfig {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Value("${etrat-warrper-url}")
    private String etarturl;

    @Bean
    public HesabDTO getZohaHesab() {
        List<TransactionType> all = transactionTypeRepository.findAll();
        if (all.isEmpty()) {
            HesabDTO body = restTemplate.getForEntity(etarturl + "/api/v1/payment-type", HesabDTO.class).getBody();
            body
                .getHesabDTOS()
                .stream()
                .forEach(
                    h -> {
                        TransactionType transactionType = new TransactionType();
                        transactionType.setId(h.getCodehesab());
                        transactionType.setDescription(h.getNamehesab());
                        transactionTypeRepository.save(transactionType);
                    }
                );
            return body;
        } else {
            HesabDTO hesabDTO = new HesabDTO();
            all
                .stream()
                .forEach(
                    h -> {
                        ZohaHesab transactionType = new ZohaHesab();
                        transactionType.setCodehesab(h.getId().toString());
                        transactionType.setNamehesab(h.getDescription());
                        hesabDTO.getHesabDTOS().add(transactionType);
                    }
                );
            return hesabDTO;
        }
    }
}
