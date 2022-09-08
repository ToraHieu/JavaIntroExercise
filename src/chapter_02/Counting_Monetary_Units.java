package chapter_02;

import java.util.*;

public class Counting_Monetary_Units {
	private static Scanner in;

	public static void main (String[] agrs) {
		in = new Scanner(System.in);
		
		System.out.print("Enter the amount: ");
		double total = in.nextDouble();
		int remaining = (int) (total*100);

		int dollars = remaining / 100;
		remaining %= 100;
		
		int quarters = remaining / 25;
		remaining %= 25;
		
		int dimes = remaining / 10;
		remaining %= 10;
		
		int nickels = remaining / 5;
		remaining %= 5;
		
		int pennies = remaining;
		
		System.out.println("Your " + total + " consists of \n" 
				+ "\t" + dollars + " dollars "
				+ "\t" + quarters + " quarters "
				+ "\t" + dimes + " dimes "
				+ "\t" + nickels + " nickels "
				+ "\t" + pennies + " pennies ");
		
	}

}
