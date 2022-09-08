package chapter_02;

import java.util.*;

public class ComputeLoans {
	private static Scanner in;

	public static void main (String[] args) {
		in = new Scanner(System.in);
		
		System.out.print("Enter the Loan Amount: "); 
		double loan = in.nextDouble();
		
		System.out.print("Enter the Annual Interest rate (%): ");
		double annualInterest = in.nextDouble();
		double monthlyInterest = annualInterest / 1200; 
		
		System.out.print("Enter the number of years: ");
		double year = in.nextDouble();
		
		double monthlyPayment = (loan * monthlyInterest) / (1 - (1 / Math.pow(1 + monthlyInterest, year * 12) )); 
		
		double totalPayment = monthlyPayment * year * 12;
		
		System.out.println("Monthly Payment is: " + (int) (monthlyPayment*100) / 100.0);
		System.out.println("Total Payment is: " + (int) (totalPayment*100) / 100.0);
	}

}
