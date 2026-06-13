using LarpLedgerWallet.Services;

var ledger = new LedgerService();

Console.WriteLine("=== LARP Ledger Wallet (Fake Balance Emulator) ===");
Console.WriteLine("Commands: add <desc> <amt> | revert <id> | list | balance | exit\n");

while (true)
{
    Console.Write("> ");
    var input = Console.ReadLine()?.Trim();
    if (string.IsNullOrEmpty(input)) continue;

    var parts = input.Split(' ', StringSplitOptions.RemoveEmptyEntries);
    var cmd = parts[0].ToLower();

    switch (cmd)
    {
        case "add" when parts.Length >= 3:
            var desc = string.Join(' ', parts[1..^1]);
            if (decimal.TryParse(parts[^1], out var amt))
            {
                ledger.AddEntry(desc, amt);
                Console.WriteLine($"Added: {desc} ({amt:F2})");
            }
            else Console.WriteLine("Invalid amount.");
            break;

        case "revert" when parts.Length == 2:
            if (ledger.RevertEntry(parts[1]))
                Console.WriteLine("Reverted.");
            else Console.WriteLine("Entry not found.");
            break;

        case "list":
            foreach (var e in ledger.Entries)
                Console.WriteLine($"{e.Id} | {e.Timestamp:HH:mm:ss} | {e.Description} | {e.Amount,10:F2}");
            break;

        case "balance":
            Console.WriteLine($"Current balance: {ledger.Balance:F2}");
            break;

        case "exit":
            return;

        default:
            Console.WriteLine("Unknown command.");
            break;
    }
}