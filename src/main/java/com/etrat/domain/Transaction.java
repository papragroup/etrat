package com.etrat.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rrn")
    private String rrn;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "refrence")
    private Long refrence;

    @OneToOne
    @JoinColumn(unique = true)
    private TransactionType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRrn() {
        return rrn;
    }

    public Transaction rrn(String rrn) {
        this.rrn = rrn;
        return this;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getRefrence() {
        return refrence;
    }

    public Transaction refrence(Long refrence) {
        this.refrence = refrence;
        return this;
    }

    public void setRefrence(Long refrence) {
        this.refrence = refrence;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction type(TransactionType transactionType) {
        this.type = transactionType;
        return this;
    }

    public void setType(TransactionType transactionType) {
        this.type = transactionType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", rrn='" + getRrn() + "'" +
            ", amount=" + getAmount() +
            ", refrence=" + getRefrence() +
            "}";
    }
}
