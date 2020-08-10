package com.etrat.util;

import java.util.List;

public class VerifyResponse {
    private Integer amount;
    private String cardNumber;
    private String cardHashPan;
    private List<Merchant> merchants = null;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHashPan() {
        return cardHashPan;
    }

    public void setCardHashPan(String cardHashPan) {
        this.cardHashPan = cardHashPan;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }
}
