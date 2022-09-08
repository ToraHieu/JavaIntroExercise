package chapter_06;

import java.util.Scanner;

public class Ex_05 {
	private static Scanner in = new Scanner (System.in);
	
	public static void displaySortedNumber (double num1, double num2, double num3) {
		if (num1 <= num2 && num1 <= num3) {
			if (num2 <= num3) 
				System.out.println(num1 + " " + num2 + " " + num3);
			else 
				System.out.println(num1 + " " + num3 + " " + num2);
		}
		else if (num2 <= num1 && num2 <= num3) {
			if (num1 <= num3)
				System.out.println(num2 + " " + num1 + " " + num3);
			else 
				System.out.println(num2 + " " + num3 + " " + num1);
		}
		else {
			if (num1 < num2)
				System.out.println(num3 + " " + num1 + " " + num2);
			else 
				System.out.println(num3 + " " + num2 + " " + num1);
		}
		
	}
	
	public static void main (String[] agrs) {
		System.out.println("Enter 3 numbers: ");
		double num1 = in.nextDouble();
		double num2 = in.nextDouble();
		double num3 = in.nextDouble();
		
		displaySortedNumber(num1, num2, num3);
	}

}
