package service;

import model.Account;

import java.util.List;
import java.util.Random;

public class TransactionTask implements Runnable {

    private final Bank bank;
    private final Random random = new Random();

    public TransactionTask(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        List<Account> accounts = bank.getAccounts();
        for (int i = 0; i < 50; i++) {
            int fromIndex = random.nextInt(accounts.size());
            int toIndex;
            do {
                toIndex = random.nextInt(accounts.size());
            } while (fromIndex == toIndex);

            double amount = random.nextDouble() * 200;

            try {
                boolean result = bank.transferMoney(accounts.get(fromIndex), accounts.get(toIndex), amount);
                if (result) {
                    AsyncService.sendEmailAsync("Transfer success: $" + amount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
