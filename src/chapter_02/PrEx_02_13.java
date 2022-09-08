package chapter_02;

import java.util.*;

public class PrEx_02_13 {
	
	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		System.out.print("Enter a monthly saving amount: ");
		double saving = in.nextDouble();
		
		double totalInterest = 1.00417;
		
		double firstMonth = saving * totalInterest;
		double secondMonth = (firstMonth + saving) * totalInterest;
		double thirdMonth = (secondMonth + saving) * totalInterest;
		double fourthMonth = (thirdMonth + saving) * totalInterest;
		double fifthMonth = (fourthMonth + saving) * totalInterest;
		double sixthMonth = (fifthMonth + saving) * totalInterest;
		
		System.out.print("After 6 months, the account value is: "+ ((int)(sixthMonth * 100) / 100.0));
 	
	}

}
