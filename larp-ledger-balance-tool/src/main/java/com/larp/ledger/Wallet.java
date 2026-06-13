package com.larp.ledger;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private final String owner;
    private final Map<String, Long> balances;

    public Wallet(String owner) {
        this.owner = owner;
        this.balances = new HashMap<>();
    }

    public String getOwner() {
        return owner;
    }

    public long getBalance(String asset) {
        return balances.getOrDefault(asset, 0L);
    }

    public void credit(String asset, long amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot credit negative amount");
        balances.merge(asset, amount, Long::sum);
    }

    public boolean debit(String asset, long amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot debit negative amount");
        long current = getBalance(asset);
        if (current < amount) return false;
        balances.put(asset, current - amount);
        return true;
    }

    public Map<String, Long> getAllBalances() {
        return new HashMap<>(balances);
    }
}