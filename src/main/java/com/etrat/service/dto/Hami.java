package com.etrat.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Hami {
    private Integer hamiId;

    private Integer shenase;
    private String firstName;

    private String lastName;

    private String CellPhone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public void setCellPhone(String cellPhone) {
        CellPhone = cellPhone;
    }

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
