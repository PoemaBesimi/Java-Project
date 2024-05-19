package com.bankapp;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionCreation() {
        Transaction paymentTransaction = new Transaction(250.0, "acc123", "chk456", "Payment for services");
        assertEquals(new BigDecimal("250.00"), paymentTransaction.getAmount());
        assertEquals("acc123", paymentTransaction.getOriginatingAccountId());
        assertEquals("chk456", paymentTransaction.getResultingAccountId());
        assertEquals("Payment for services", paymentTransaction.getReason());
    }

    @Test
    void testFormattedAmount() {
        Transaction refundTransaction = new Transaction(125.0, "biz789", "acc123", "Refund");
        assertEquals("$125.00", refundTransaction.getFormattedAmount());
    }
}
