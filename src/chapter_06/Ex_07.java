package chapter_06;

import java.util.Scanner;
public class Ex_07 {
	private static Scanner in = new Scanner (System.in);
	
	public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years) {
		double futureValue = investmentAmount * Math.pow((1 + monthlyInterestRate),years*12);
		return futureValue;
	}
	
	public static void main (String[] agrs)	 {
		System.out.print("The amount invested: ");
		double amountInvested = in.nextDouble();
		System.out.print("Annual interest rate: ");
		double interestRate = in.nextDouble();
		System.out.println("Years" + "          " + "Future Value");
		for (int i = 1; i <= 30; i++) {
			System.out.printf("%-2d",i);
			System.out.printf("          %15.2f",futureInvestmentValue(amountInvested, interestRate /100 / 12, i));
			System.out.println();
		}
		
		System.out.println(futureInvestmentValue(amountInvested, interestRate /100 / 12, 500));
	}

}
