package service;

import model.Account;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private final AtomicInteger successfulTransactions = new AtomicInteger(0);
    private final Semaphore transactionSemaphore;

    public Bank(int numberOfAccounts, int maxConcurrentTransactions) {
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i, 1000.0));
        }
        this.transactionSemaphore = new Semaphore(maxConcurrentTransactions);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean transferMoney(Account from, Account to, double amount) throws InterruptedException {
        transactionSemaphore.acquire();
        boolean success = false;

        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    successfulTransactions.incrementAndGet();
                    success = true;
                }
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
            transactionSemaphore.release();
        }
        return success;
    }

    public int getSuccessfulTransactions() {
        return successfulTransactions.get();
    }

    public double getTotalBalance() {
        return accounts.stream().mapToDouble(Account::getBalance).sum();
    }

    public List<Account> getHighBalanceAccounts(double threshold) {
        return accounts.parallelStream()
                .filter(acc -> acc.getBalance() > threshold)
                .toList();
    }
}
