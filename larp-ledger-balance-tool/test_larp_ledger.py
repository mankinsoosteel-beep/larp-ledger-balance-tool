"""Tests for LarpLedger."""

import pytest
from ledger import LarpLedger

def test_initial_balance():
    ledger = LarpLedger()
    assert ledger.get_balance() == 1000.0

def test_send_valid_transaction():
    ledger = LarpLedger()
    result = ledger.send_transaction(100.0, "fake_addr_123")
    assert "Error" not in result
    assert ledger.get_balance() == 900.0

def test_send_insufficient_balance():
    ledger = LarpLedger()
    result = ledger.send_transaction(9999.0, "addr")
    assert "Insufficient" in result
    assert ledger.get_balance() == 1000.0

def test_send_negative_amount():
    ledger = LarpLedger()
    result = ledger.send_transaction(-50.0, "addr")
    assert "positive" in result

def test_transaction_history_empty():
    ledger = LarpLedger()
    assert ledger.get_transaction_history() == []

def test_transaction_history_after_send():
    ledger = LarpLedger()
    ledger.send_transaction(50.0, "dest")
    history = ledger.get_transaction_history()
    assert len(history) == 1
    assert "50.0 LARP" in history[0]