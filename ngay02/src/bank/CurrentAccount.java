package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, Person owner, double initialBalance, LocalDate createdDate, double overdraftLimit) {
        super(accountNumber, owner, initialBalance, createdDate, AccountType.CURRENT);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance + overdraftLimit >= amount) { // Cho phép thấu chi
            balance -= amount;
            transactions.add(new Transaction("TX" + (transactions.size() + 1), TransactionType.WITHDRAW, amount, LocalDateTime.now()));
            System.out.println("Withdrawn: " + amount);
            return true;
        } else {
            System.out.println("Exceeds overdraft limit or invalid amount");
            return false;
        }
    }
}