package com.etrat.schadule;

import com.etrat.util.EtratWarpperUtil;
import com.etrat.web.rest.UserJWTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IntegrationJob {
    @Autowired
    private EtratWarpperUtil etratWarpperUtil;

    @Autowired
    private UserJWTController userJWTController;

    @Scheduled(fixedDelay = 60000)
    public void integrationWithEtart() {
        etratWarpperUtil.getHami();
    }

    @Scheduled(fixedDelay = 6000)
    public void removeExpireOtp() {
        userJWTController.removeExpire();
    }
}
