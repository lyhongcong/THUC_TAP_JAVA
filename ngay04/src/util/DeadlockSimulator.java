package util;

import model.Account;

public class DeadlockSimulator {
    public static void createDeadlock(Account a, Account b) {
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(100);
                    synchronized (b) {
                        System.out.println("T1 acquired both locks");
                    }
                } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(100);
                    synchronized (a) {
                        System.out.println("T2 acquired both locks");
                    }
                } catch (InterruptedException ignored) {}
            }
        });

        t1.start();
        t2.start();
    }
}
