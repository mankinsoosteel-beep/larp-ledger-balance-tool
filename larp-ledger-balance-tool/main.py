#!/usr/bin/env python3
"""Larp Ledger Balance Tool - Main entry point."""

from ledger import LarpLedger
from ui import print_menu, get_user_choice

def main():
    ledger = LarpLedger()
    print("=== LARP LEDGER BALANCE TOOL ===")
    print("Fake balance emulator v1.0")
    print()

    while True:
        print_menu()
        choice = get_user_choice()
        if choice == '1':
            balance = ledger.get_balance()
            print(f"\nCurrent balance: {balance} LARP\n")
        elif choice == '2':
            try:
                amount = float(input("Amount to send (LARP): "))
                to_address = input("Destination address: ")
                result = ledger.send_transaction(amount, to_address)
                print(f"\n{result}\n")
            except ValueError:
                print("\nInvalid amount.\n")
        elif choice == '3':
            history = ledger.get_transaction_history()
            print("\nTransaction History:")
            if not history:
                print("  (no transactions yet)")
            else:
                for tx in history:
                    print(f"  {tx}")
            print()
        elif choice == '4':
            print("Exiting...")
            break
        else:
            print("Invalid choice. Try again.\n")

if __name__ == "__main__":
    main()