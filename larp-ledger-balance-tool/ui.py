"""Simple terminal UI helpers."""

from colorama import init, Fore, Style

init(autoreset=True)

def print_menu():
    print(Fore.CYAN + "Menu:")
    print("  1. Check balance")
    print("  2. Send LARP")
    print("  3. Transaction history")
    print("  4. Exit" + Style.RESET_ALL)

def get_user_choice() -> str:
    return input("Enter choice (1-4): ").strip()