package com.larp.ledger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    @Test
    void testCreditAndDebit() {
        Wallet w = new Wallet("Test");
        w.credit("BTC", 10);
        assertEquals(10, w.getBalance("BTC"));
        assertTrue(w.debit("BTC", 3));
        assertEquals(7, w.getBalance("BTC"));
    }

    @Test
    void testDebitInsufficientFunds() {
        Wallet w = new Wallet("Test");
        w.credit("ETH", 5);
        assertFalse(w.debit("ETH", 10));
        assertEquals(5, w.getBalance("ETH"));
    }

    @Test
    void testNegativeAmountThrows() {
        Wallet w = new Wallet("Test");
        assertThrows(IllegalArgumentException.class, () -> w.credit("XRP", -1));
        assertThrows(IllegalArgumentException.class, () -> w.debit("XRP", -5));
    }

    @Test
    void testDefaultZeroBalance() {
        Wallet w = new Wallet("Empty");
        assertEquals(0, w.getBalance("NONEXISTENT"));
    }
}