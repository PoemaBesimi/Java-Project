package com.bankapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {
    private BigDecimal amount;
    private String originatingAccountId;
    private String resultingAccountId;
    private String reason;
    
    public Transaction(double amount, String originatingAccountId, String resultingAccountId, String reason) {
        this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN);
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getOriginatingAccountId() {
        return originatingAccountId;
    }

    public String getResultingAccountId() {
        return resultingAccountId;
    }

    public String getReason() {
        return reason;
    }
    public String getFormattedAmount() {
        return "$" + amount.setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
