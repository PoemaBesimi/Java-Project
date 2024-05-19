package com.bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank cityBank;
    private Account checkingAccount;
    private Account businessAccount;

    @BeforeEach
    void setUp() {
        cityBank = new Bank("City Bank", 2.0, 1.5);
        checkingAccount = new Account("chk456", "Bob Johnson", 1000.0);
        businessAccount = new Account("biz789", "Corporate Inc.", 5000.0);
        cityBank.addAccount(checkingAccount);
        cityBank.addAccount(businessAccount);
    }

    @Test
    void testTransferFlatFee() {
        cityBank.transfer("chk456", "biz789", 200.0, true);
        assertEquals(new BigDecimal("798.00"), checkingAccount.getBalance());
        assertEquals(new BigDecimal("5200.00"), businessAccount.getBalance());
        assertEquals(new BigDecimal("2.00"), cityBank.getTotalTransactionFeeAmount());
        assertEquals(new BigDecimal("200.00"), cityBank.getTotalTransferAmount());
    }

    @Test
    void testTransferPercentFee() {
        cityBank.transfer("chk456", "biz789", 200.0, false);
        assertEquals(new BigDecimal("797.00"), checkingAccount.getBalance());
        assertEquals(new BigDecimal("5200.00"), businessAccount.getBalance());
        assertEquals(new BigDecimal("3.00"), cityBank.getTotalTransactionFeeAmount());
        assertEquals(new BigDecimal("200.00"), cityBank.getTotalTransferAmount());
    }

    @Test
    void testTransferInsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> {
            cityBank.transfer("chk456", "biz789", 1200.0, true);
        });
    }

    @Test
    void testAddAccount() {
        Account personalAccount = new Account("per001", "Charlie Brown", 300.0);
        cityBank.addAccount(personalAccount);
        assertEquals(3, cityBank.getAccounts().size());
        assertEquals(personalAccount, cityBank.getAccount("per001"));
    }

    @Test
    void testGetAccountNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            cityBank.getAccount("nonexistent");
        });
    }
}
