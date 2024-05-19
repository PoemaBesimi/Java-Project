package com.bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankIntegrationTest {

    private Bank metroBank;
    private Account savingsAccount;
    private Account checkingAccount;

    @BeforeEach
    void setUp() {
        metroBank = new Bank("Metro Bank", 1.0, 2.0);
        savingsAccount = new Account("sav101", "Diana Prince", 2000.0);
        checkingAccount = new Account("chk202", "Bruce Wayne", 3000.0);
        metroBank.addAccount(savingsAccount);
        metroBank.addAccount(checkingAccount);
    }

    @Test
    void testFullTransactionCycle() {
        // Create accounts
        Account travelAccount = new Account("trav303", "Clark Kent", 1000.0);
        metroBank.addAccount(travelAccount);
        assertEquals(3, metroBank.getAccounts().size());

        // Deposit money
        travelAccount.deposit(500.0);
        assertEquals(new BigDecimal("1500.00"), travelAccount.getBalance());

        // Withdraw money
        travelAccount.withdraw(200.0);
        assertEquals(new BigDecimal("1300.00"), travelAccount.getBalance());

        // Transfer money with flat fee
        metroBank.transfer("sav101", "chk202", 500.0, true);
        assertEquals(new BigDecimal("1499.00"), savingsAccount.getBalance());
        assertEquals(new BigDecimal("3500.00"), checkingAccount.getBalance());

        // Transfer money with percent fee
        metroBank.transfer("chk202", "trav303", 300.0, false);
        assertEquals(new BigDecimal("3194.00"), checkingAccount.getBalance());
        assertEquals(new BigDecimal("1600.00"), travelAccount.getBalance());
    }
}
