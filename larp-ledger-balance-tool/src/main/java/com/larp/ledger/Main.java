package com.larp.ledger;

public class Main {
    public static void main(String[] args) {
        LedgerService service = new LedgerService();

        Wallet alice = service.createWallet("Alice");
        Wallet bob = service.createWallet("Bob");

        alice.credit("GOLD", 1000);
        alice.credit("SILVER", 500);
        bob.credit("GOLD", 200);

        System.out.println("=== LARP Ledger Fake Balances ===");
        System.out.println("Alice GOLD: " + alice.getBalance("GOLD"));
        System.out.println("Alice SILVER: " + alice.getBalance("SILVER"));
        System.out.println("Bob GOLD: " + bob.getBalance("GOLD"));

        boolean ok = service.transfer("Alice", "Bob", "GOLD", 300);
        System.out.println("\nTransfer 300 GOLD from Alice to Bob: " + (ok ? "SUCCESS" : "FAILED"));

        System.out.println("Alice GOLD after: " + alice.getBalance("GOLD"));
        System.out.println("Bob GOLD after: " + bob.getBalance("GOLD"));

        ok = service.transfer("Bob", "Alice", "SILVER", 9999);
        System.out.println("\nTransfer 9999 SILVER from Bob to Alice: " + (ok ? "SUCCESS" : "FAILED (expected)"));
    }
}