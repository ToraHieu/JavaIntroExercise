package chapter_11;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    private String name;    
    private int id;
    private double balance;
    private static double annualInterestRate = 0;
    private Date dateCreated;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    
    public Account() {
        this(0,0.0);
    }
    
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        dateCreated = new Date();
    }
    
    public Account(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        dateCreated = new Date();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }
    
    public String getDateCreated() {
        return dateCreated.toString();
    }
    
    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }
    
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > balance) 
            System.out.println("Unable to withdraw. The amount entered is greater than the balance.");
        else {
            balance-= withdrawAmount;
            transactions.add(new Transaction(new Date(), 'W', withdrawAmount, balance, "Withdraw succesful"));
            System.out.println("Withdraw successful. The current balance is " + balance + ".");
        }
    }
    
    public void deposit(double depositAmount) {
        balance+= depositAmount;
        transactions.add(new Transaction(new Date(), 'D', depositAmount, balance, "Deposit succesful"));
        System.out.println("Deposist successful. The current balance is " + balance + ".");
    }
    
}
