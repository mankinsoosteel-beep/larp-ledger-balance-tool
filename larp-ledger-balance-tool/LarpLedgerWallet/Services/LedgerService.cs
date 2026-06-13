using LarpLedgerWallet.Models;

namespace LarpLedgerWallet.Services;

public class LedgerService
{
    private readonly List<LedgerEntry> _entries = new();

    public decimal Balance => _entries.Sum(e => e.Amount);

    public IReadOnlyList<LedgerEntry> Entries => _entries.AsReadOnly();

    public void AddEntry(string description, decimal amount)
    {
        _entries.Add(new LedgerEntry
        {
            Description = description,
            Amount = amount
        });
    }

    public bool RevertEntry(string id)
    {
        var entry = _entries.FirstOrDefault(e => e.Id == id);
        if (entry == null) return false;

        _entries.Add(new LedgerEntry
        {
            Description = $"Revert: {entry.Description}",
            Amount = -entry.Amount
        });
        return true;
    }
}