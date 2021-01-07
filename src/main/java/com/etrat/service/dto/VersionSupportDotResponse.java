package com.etrat.service.dto;

public class VersionSupportDotResponse {
    private Boolean isAppVersionSupported = Boolean.TRUE;
    private String nextAppUrl;
    private String message;

    public Boolean getAppVersionSupported() {
        return isAppVersionSupported;
    }

    public void setAppVersionSupported(Boolean appVersionSupported) {
        isAppVersionSupported = appVersionSupported;
    }

    public String getNextAppUrl() {
        return nextAppUrl;
    }

    public void setNextAppUrl(String nextAppUrl) {
        this.nextAppUrl = nextAppUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
