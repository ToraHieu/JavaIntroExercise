package chapter_32;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class Ex_08 {
    private static Account account = new Account();

    public static void main(String[] args) {
        System.out.println("Thread 1\tThread 2\tBalance");

        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();
    }

    // A task for adding an amount to the account
    public static class DepositTask implements Runnable {
        public void run() {
            try { // Purposely delay it to let the withdraw method proceed
                while (true) {
                    account.deposit((int)(Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // A task for subtracting an amount from the account
    public static class WithdrawTask implements Runnable {
        public void run() {
            while (true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    // An inner class for account
    private static class Account {
        // Create a condition
        private AtomicInteger balance = new AtomicInteger(0);

        public int getBalance() {
            return balance.get();
        }

        public void withdraw(Integer amount) {
            synchronized (balance) {
                try {
                    while (balance.get() < amount) {
                        System.out.println("\t\t\tWait for a deposit");
                        balance.wait();
                    }
                    balance.getAndAdd(-amount);
                    System.out.println("\t\t\tWithdraw " + amount +
                            "\t\t" + getBalance());
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }

        public void deposit(int amount) {
            synchronized (balance) {
                System.out.println("Deposit " + amount +
                        "\t\t\t\t\t" + balance.addAndGet(amount));

                // Signal thread waiting on the condition
                balance.notifyAll();
            }
        }
    }
}
