package util;

import model.Account;
import service.Bank;

public class ReportGenerator {
    public static void generate(Bank bank) {
        System.out.println("\n=== BANK REPORT ===");
        System.out.println("Total Balance: $" + bank.getTotalBalance());
        System.out.println("Accounts with balance > 1500:");
        for (Account acc : bank.getHighBalanceAccounts(1500)) {
            System.out.println("Account " + acc.getId() + ": $" + acc.getBalance());
        }
    }
}
