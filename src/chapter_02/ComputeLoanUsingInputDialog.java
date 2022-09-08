package chapter_02;

import javax.swing.*;

public class ComputeLoanUsingInputDialog {
	public static void main (String[] agrs) {
		
		String annualInterestS = JOptionPane.showInputDialog("Enter annual interest rate: ");
		double annualInterest = Double.parseDouble(annualInterestS);
		double monthlyInterest = annualInterest / 1200;
		
		String yearS = JOptionPane.showInputDialog("Enter number of years: ");
		int year = Integer.parseInt(yearS);
		
		String loanS = JOptionPane.showInputDialog("Enter the loan amount: ");
		double loan = Double.parseDouble(loanS);
		
		double monthlyPayment = (loan * monthlyInterest) / (1 - (1 / Math.pow(1 + monthlyInterest, year * 12) )); 
		
		double totalPayment = monthlyPayment * year * 12;
		
		monthlyPayment = (int)(monthlyPayment * 100) / 100.0;
		totalPayment = (int)(totalPayment * 100) / 100.0;
		String output = "The monthly payment is: " + monthlyPayment + "\n" + "The total payment is: " +totalPayment;
		JOptionPane.showMessageDialog(null, output);
	}

}
