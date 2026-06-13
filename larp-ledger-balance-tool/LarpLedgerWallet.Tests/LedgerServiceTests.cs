using LarpLedgerWallet.Services;

namespace LarpLedgerWallet.Tests;

public class LedgerServiceTests
{
    public static void RunAll()
    {
        TestInitialBalanceZero();
        TestAddEntryIncreasesBalance();
        TestRevertExistingEntry();
        TestRevertNonExistingEntry();
        Console.WriteLine("All tests passed.");
    }

    static void TestInitialBalanceZero()
    {
        var svc = new LedgerService();
        if (svc.Balance != 0) throw new Exception("Initial balance should be 0.");
    }

    static void TestAddEntryIncreasesBalance()
    {
        var svc = new LedgerService();
        svc.AddEntry("test", 100);
        if (svc.Balance != 100) throw new Exception("Balance should be 100.");
    }

    static void TestRevertExistingEntry()
    {
        var svc = new LedgerService();
        svc.AddEntry("test", 50);
        var id = svc.Entries[0].Id;
        var ok = svc.RevertEntry(id);
        if (!ok || svc.Balance != 0) throw new Exception("Revert failed.");
    }

    static void TestRevertNonExistingEntry()
    {
        var svc = new LedgerService();
        var ok = svc.RevertEntry("nonexistent");
        if (ok) throw new Exception("Should not revert non-existing.");
    }
}