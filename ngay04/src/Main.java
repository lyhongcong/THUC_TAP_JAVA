import service.Bank;
import service.TransactionTask;
import util.ReportGenerator;
import util.DeadlockSimulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank(20, 10); // 20 accounts, max 10 transactions at a time
        ExecutorService executor = Executors.newFixedThreadPool(50); // 50 threads

        // Khởi chạy hàng trăm giao dịch đồng thời
        for (int i = 0; i < 100; i++) {
            executor.submit(new TransactionTask(bank));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(1000);
        }

        // Thử tạo deadlock và giải thích
        System.out.println("\n Giả lập deadlock:");
        DeadlockSimulator.createDeadlock(bank.getAccounts().get(0), bank.getAccounts().get(1));
        Thread.sleep(500);

        // Báo cáo
        ReportGenerator.generate(bank);
        System.out.println("Tổng số giao dịch thành công: " + bank.getSuccessfulTransactions());
    }
}
