package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class BankAccount implements Printable {
    protected String accountNumber;
    protected Person owner;
    protected double balance;
    protected LocalDate createdDate;
    protected ArrayList<Transaction> transactions;
    protected AccountType accountType;

    // Inner class Transaction
    public class Transaction {
        private String id;
        private TransactionType type;
        private double amount;
        private LocalDateTime timestamp;

        public Transaction(String id, TransactionType type, double amount, LocalDateTime timestamp) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Transaction ID: " + id + ", Type: " + type + ", Amount: " + amount + ", Time: " + timestamp;
        }
    }

    // Constructor
    public BankAccount(String accountNumber, Person owner, double initialBalance, LocalDate createdDate, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.createdDate = createdDate;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    // Phương thức trừu tượng
    public abstract boolean withdraw(double amount);

    // Phương thức gửi tiền
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("TX" + (transactions.size() + 1), TransactionType.DEPOSIT, amount, LocalDateTime.now()));
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Phương thức lấy số dư
    public double getBalance() {
        return balance;
    }

    // Phương thức in thông tin tài khoản
    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + owner.getFullName());
        System.out.println("Balance: " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Created Date: " + createdDate);
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Triển khai interface Printable
    @Override
    public void printSummary() {
        System.out.println("Summary for Account " + accountNumber + ": Balance = " + balance);
    }

    // Getter cho accountNumber (dùng trong BankApp)
    public String getAccountNumber() {
        return accountNumber;
    }
}