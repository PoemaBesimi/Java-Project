package com.bankapp;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Account {
    
    private String accountId;
    private String userName;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_EVEN);
        this.transactions = new ArrayList<>();
}

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            BigDecimal depositAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN);
            this.balance = this.balance.add(depositAmount);
            this.transactions.add(new Transaction(amount, "Deposit", accountId, "Deposit"));
        } else {
            throw new IllegalArgumentException("Deposit amount must not be below zero!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            BigDecimal withdrawAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN);
            if (this.balance.compareTo(withdrawAmount) >= 0) {
                this.balance = this.balance.subtract(withdrawAmount);
                this.transactions.add(new Transaction(amount, accountId, "Withdrawal", "Withdrawal"));
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
    }

    public String getFormattedBalance() {
        return "$" + balance.setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}