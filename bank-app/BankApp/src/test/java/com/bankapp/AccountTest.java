package com.bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account savingsAccount;

    @BeforeEach
    void setUp() {
        savingsAccount = new Account("acc123", "Alice Smith", 500.0);
    }

    @Test
    void testDeposit() {
        savingsAccount.deposit(150.0);
        assertEquals(new BigDecimal("650.00"), savingsAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        savingsAccount.withdraw(100.0);
        assertEquals(new BigDecimal("400.00"), savingsAccount.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.withdraw(600.0);
        });
    }

    @Test
    void testDepositNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.deposit(-50.0);
        });
    }

    @Test
    void testWithdrawNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.withdraw(-50.0);
        });
    }
}
