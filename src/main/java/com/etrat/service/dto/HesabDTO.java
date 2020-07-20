package com.etrat.service.dto;

import java.util.ArrayList;
import java.util.List;

public class HesabDTO {

    List<ZohaHesab> hesabDTOS=new ArrayList<>();


    public List<ZohaHesab> getHesabDTOS() {
        return hesabDTOS;
    }

    public void setHesabDTOS(List<ZohaHesab> hesabDTOS) {
        this.hesabDTOS = hesabDTOS;
    }
}
