package bank;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class BankApp {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ NGÂN HÀNG ===");
            System.out.println("1. Tạo tài khoản tiết kiệm");
            System.out.println("2. Tạo tài khoản thanh toán");
            System.out.println("3. Gửi tiền");
            System.out.println("4. Rút tiền");
            System.out.println("5. Xem thông tin tài khoản");
            System.out.println("6. Thoát");
            System.out.print("Chọn một chức năng: ");

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
                    System.out.println("Đang thoát chương trình...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        }
    }

    private static void createSavingsAccount() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.print("Nhập Họ và Tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Nhập Số dư ban đầu: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Nhập Lãi suất (%): ");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();

        Person person = new Person(id, fullName, email, phoneNumber);
        BankAccount account = new SavingsAccount(accountNumber, person, initialBalance, LocalDate.now(), interestRate);
        accounts.add(account);
        System.out.println("Tạo tài khoản tiết kiệm thành công!");
    }

    private static void createCurrentAccount() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.print("Nhập Họ và Tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Nhập Số dư ban đầu: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Nhập Hạn mức thấu chi: ");
        double overdraftLimit = scanner.nextDouble();
        scanner.nextLine();

        Person person = new Person(id, fullName, email, phoneNumber);
        BankAccount account = new CurrentAccount(accountNumber, person, initialBalance, LocalDate.now(), overdraftLimit);
        accounts.add(account);
        System.out.println("Tạo tài khoản thanh toán thành công!");
    }

    private static void performDeposit() {
        System.out.print("Nhập Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Nhập Số tiền gửi: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        } else {
            System.out.println("Không tìm thấy tài khoản!");
        }
    }

    private static void performWithdraw() {
        System.out.print("Nhập Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Nhập Số tiền rút: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        } else {
            System.out.println("Không tìm thấy tài khoản!");
        }
    }

    private static void viewAccountInfo() {
        System.out.print("Nhập Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.printAccountInfo();
            account.printSummary();
        } else {
            System.out.println("Không tìm thấy tài khoản!");
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
