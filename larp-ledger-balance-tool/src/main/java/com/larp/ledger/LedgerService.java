package com.larp.ledger;

import java.util.LinkedHashMap;
import java.util.Map;

public class LedgerService {
    private final Map<String, Wallet> wallets;

    public LedgerService() {
        this.wallets = new LinkedHashMap<>();
    }

    public Wallet createWallet(String owner) {
        if (wallets.containsKey(owner)) {
            throw new IllegalArgumentException("Wallet already exists for: " + owner);
        }
        Wallet w = new Wallet(owner);
        wallets.put(owner, w);
        return w;
    }

    public Wallet getWallet(String owner) {
        Wallet w = wallets.get(owner);
        if (w == null) throw new IllegalArgumentException("Wallet not found: " + owner);
        return w;
    }

    public boolean transfer(String fromOwner, String toOwner, String asset, long amount) {
        Wallet from = getWallet(fromOwner);
        Wallet to = getWallet(toOwner);
        if (from.debit(asset, amount)) {
            to.credit(asset, amount);
            return true;
        }
        return false;
    }

    public int walletCount() {
        return wallets.size();
    }
}