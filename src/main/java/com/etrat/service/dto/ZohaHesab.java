package com.etrat.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ZohaHesab {


    private String codehesab;

    private String namehesab;


    public String getCodehesab() {
        return codehesab;
    }

    public void setCodehesab(String codehesab) {
        this.codehesab = codehesab;
    }

    public String getNamehesab() {
        return namehesab;
    }

    public void setNamehesab(String namehesab) {
        this.namehesab = namehesab;
    }
}
