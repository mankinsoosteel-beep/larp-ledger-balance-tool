"""Core ledger logic for LARP balance emulation."""

import random
import time

class LarpLedger:
    """Simulates a hardware ledger with a fake balance."""

    def __init__(self):
        self.balance = 1000.0
        self.transactions = []

    def get_balance(self) -> float:
        return self.balance

    def send_transaction(self, amount: float, to_address: str) -> str:
        if amount <= 0:
            return "Error: Amount must be positive."
        if amount > self.balance:
            return "Error: Insufficient LARP balance."
        # Simulate a small delay like a real ledger
        time.sleep(0.5)
        self.balance -= amount
        tx_id = f"LARP-{random.randint(100000, 999999)}"
        self.transactions.append({
            'tx_id': tx_id,
            'amount': amount,
            'to': to_address,
            'timestamp': time.strftime("%Y-%m-%d %H:%M:%S")
        })
        return f"Transaction {tx_id}: Sent {amount} LARP to {to_address}"

    def get_transaction_history(self) -> list:
        return [f"{t['timestamp']} | {t['tx_id']} | {t['amount']} LARP -> {t['to']}" for t in self.transactions]