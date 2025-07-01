package bank;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class BankApp {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Account Info");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    createSavingsAccount();
                    break;
                case 2:
                    createCurrentAccount();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performWithdraw();
                    break;
                case 5:
                    viewAccountInfo();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void createSavingsAccount() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Enter Interest Rate: ");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();

        Person person = new Person(id, fullName, email, phoneNumber);
        BankAccount account = new SavingsAccount(accountNumber, person, initialBalance, LocalDate.now(), interestRate);
        accounts.add(account);
        System.out.println("Savings Account created successfully!");
    }

    private static void createCurrentAccount() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Enter Overdraft Limit: ");
        double overdraftLimit = scanner.nextDouble();
        scanner.nextLine();

        Person person = new Person(id, fullName, email, phoneNumber);
        BankAccount account = new CurrentAccount(accountNumber, person, initialBalance, LocalDate.now(), overdraftLimit);
        accounts.add(account);
        System.out.println("Current Account created successfully!");
    }

    private static void performDeposit() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    private static void performWithdraw() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter Withdraw Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    private static void viewAccountInfo() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.printAccountInfo();
            account.printSummary();
        } else {
            System.out.println("Account not found");
        }
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}