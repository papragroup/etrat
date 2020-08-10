package com.etrat.web.rest.errors;

public class InvalidAmountException extends BadRequestAlertException {
    private static final long serialVersionUID = 1L;

    public InvalidAmountException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}
