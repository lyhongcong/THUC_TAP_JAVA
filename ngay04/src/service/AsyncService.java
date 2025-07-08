package service;

import java.util.concurrent.CompletableFuture;

public class AsyncService {
    public static void sendEmailAsync(String message) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(50); // Mô phỏng chậm
                System.out.println("- Email sent: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
