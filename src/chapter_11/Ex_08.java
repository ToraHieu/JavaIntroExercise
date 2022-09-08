package chapter_11;

import chapter_11.Account;
import chapter_11.Transaction;

public class Ex_08 {
    public static void main(String[] agrs) {
        Account account = new Account("George", 1122, 1000);
        Account.setAnnualInterestRate(1.5);
        account.deposit(30);
        account.deposit(40);
        account.deposit(50);
        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);
        printSummary(account);
    }
    
    public static void printSummary(Account account) {
        System.out.println("------------------------");
        System.out.println("Account holder: " + account.getName());
        System.out.println("Interest rate: " + account.getMonthlyInterestRate());
        System.out.println("Balance: " + account.getBalance());
        for (Transaction t: account.getTransactions()) {
            System.out.println(t.toString());
        }
    }

}
