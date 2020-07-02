package com.etrat.schadule;

import com.etrat.util.EtratWarpperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IntegrationJob {

    @Autowired
    private EtratWarpperUtil etratWarpperUtil;
    @Scheduled(fixedDelay = 100000000)
    public void  integrationWithEtart(){
        etratWarpperUtil.getHami();

    }
}
