package chapter_09;
import java.util.Date;
public class Account {
	private int id;
	private double balance;
	private static double annualInterestRate = 0;
	private Date dateCreated;
	
	public Account() {
		id = 0;
		balance = 0;
		dateCreated = new Date();
	}
	
	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
		dateCreated = new Date();
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
	
	public double getMonthlyInterest() {
		double monthlyInterest;
		monthlyInterest = balance * getMonthlyInterestRate() / 100;
		
		return monthlyInterest;
	}
	
	public void withdraw(double withdrawAmount) {
		if (withdrawAmount > balance) 
			System.out.println("Unable to withdraw. The amount entered is greater than the balance.");
		else {
			balance-= withdrawAmount;
			System.out.println("Withdraw successful. The current balance is " + balance + ".");
		}
	}
	
	public void deposit(double depositAmount) {
		balance+= depositAmount;
		System.out.println("Deposist successful. The current balance is " + balance + ".");
	}
	
}
