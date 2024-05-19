package com.bankapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private BigDecimal totalTransactionFeeAmount;
    private BigDecimal totalTransferAmount;
    private BigDecimal transactionFlatFeeAmount;
    private BigDecimal transactionPercentFeeValue;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        this.totalTransferAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        this.transactionFlatFeeAmount = BigDecimal.valueOf(transactionFlatFeeAmount).setScale(2, RoundingMode.HALF_EVEN);
        this.transactionPercentFeeValue = BigDecimal.valueOf(transactionPercentFeeValue).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found!");
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void transfer(String fromAccountId, String toAccountId, double amount, boolean isFlatFee) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        BigDecimal transferAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal fee = isFlatFee ? transactionFlatFeeAmount : transferAmount.multiply(transactionPercentFeeValue).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_EVEN);

        if (fromAccount.getBalance().compareTo(transferAmount.add(fee)) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transfer");
        }

        fromAccount.withdraw(transferAmount.add(fee).doubleValue());
        toAccount.deposit(transferAmount.doubleValue());
        totalTransactionFeeAmount = totalTransactionFeeAmount.add(fee);
        totalTransferAmount = totalTransferAmount.add(transferAmount);

        Transaction transaction = new Transaction(amount, fromAccountId, toAccountId, isFlatFee ? "Flat Fee Transfer" : "Percent Fee Transfer");
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }

    public BigDecimal getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public BigDecimal getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public String getFormattedTotalTransactionFeeAmount() {
        return "$" + totalTransactionFeeAmount.setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String getFormattedTotalTransferAmount() {
        return "$" + totalTransferAmount.setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public List<Transaction> getTransactionsForAccount(String accountId) {
        Account account = getAccount(accountId);
        return account.getTransactions();
    }
}