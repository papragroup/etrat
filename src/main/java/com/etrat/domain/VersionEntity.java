package com.etrat.domain;

import com.etrat.service.dto.OperationSystem;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A Transaction.
 */
@Entity
@Table(name = "version_system")
public class VersionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "next_app_version")
    private String nextAppVersion;

    @Column(name = "next_app_url")
    private String nextAppUrl;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_system")
    private OperationSystem operationSystem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getNextAppVersion() {
        return nextAppVersion;
    }

    public void setNextAppVersion(String nextAppVersion) {
        this.nextAppVersion = nextAppVersion;
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

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
    }
}
