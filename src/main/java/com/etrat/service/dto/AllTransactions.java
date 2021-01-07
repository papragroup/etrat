package com.etrat.service.dto;

import com.etrat.domain.Transaction;
import java.util.List;

public class AllTransactions {
    private List<Transaction> transactions;

    private Long sumTransactions;

    public Long getSumTransactions() {
        return sumTransactions;
    }

    public void setSumTransactions(Long sumTransactions) {
        this.sumTransactions = sumTransactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
