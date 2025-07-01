package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, Person owner, double balance, LocalDate createdDate, double interestRate) {
        super(accountNumber, owner, balance, createdDate, AccountType.SAVINGS);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount + 5) { // Thêm phí $5
            balance -= (amount + 5);
            transactions.add(new Transaction("TX" + (transactions.size() + 1), TransactionType.WITHDRAW, amount, LocalDateTime.now()));
            System.out.println("Withdrawn: " + amount + ", Fee: 5, New balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }
}