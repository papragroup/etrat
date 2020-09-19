package com.etrat.domain;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.*;

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

    private String securePan;

    @Enumerated
    private TransactionStatus transactionStatus;

    @OneToOne
    @JoinColumn(unique = true)
    private TransactionType type;

    @Column
    private Long createDate;

    private String createDateFormat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PostLoad
    public void doStuff() {
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        DateFormat outputFormat = new SimpleDateFormat("EEE / dd MMMM yyyyy hh:mm", locale);
        this.createDateFormat = outputFormat.format(createDate);
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateFormat() {
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        DateFormat outputFormat = new SimpleDateFormat("EEE / dd MMMM yyyyy hh:mm", locale);
        return outputFormat.format(this.createDate);
    }

    public void setCreateDateFormat(String createDateFormat) {
        this.createDateFormat = createDateFormat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSecurePan() {
        return securePan;
    }

    public void setSecurePan(String securePan) {
        this.securePan = securePan;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

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
