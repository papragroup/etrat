package com.etrat.domain;

public enum TransactionStatus {
    CREATE,
    PENDING_VERIFY,
    SUCCESS_VERIFY,
    FAILED_VERIFY,
    FAILED_NOTIFY_WRAPPER,
}
