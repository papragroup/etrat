package com.etrat.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Hami {


    private Integer hamiId;

    private Integer shenase;



    public Integer getHamiId() {
        return hamiId;
    }

    public void setHamiId(Integer hamiId) {
        this.hamiId = hamiId;
    }

    public Integer getShenase() {
        return shenase;
    }

    public void setShenase(Integer shenase) {
        this.shenase = shenase;
    }
}
