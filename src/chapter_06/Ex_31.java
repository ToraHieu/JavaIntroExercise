package chapter_06;

import java.util.Scanner;

public class Ex_31 {
	private static Scanner in = new Scanner(System.in);
	
	public static void main (String[] agrs) {
		System.out.println("Enter a credit card number as a long integer: ");
		long number = in.nextLong();
		if (isValid(number))
			System.out.println(number + " is valid");
		else 
			System.out.println(number + " is invalid");
	}

	
	public static boolean isValid(long number) {
		if ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0)
			return true;
		return false;
	}
	
	public static int sumOfDoubleEvenPlace(long number) {
		int sumEven = 0;
		number /=10;
		while (number > 0) {
			long digit = (number % 10)*2;
			sumEven += getDigit((int)digit);
			number /= 100;
		}
		return sumEven;
	}
	
	public static int getDigit(int number) {
		if (number >= 10) {
			int digit1 = number%10;
			number /=10;
			int digit2 = number;
			number = digit1 + digit2;
			return number;
		}
		return number;
	}
	
	public static int sumOfOddPlace(long number) {
		int sumOdd = 0;
		while (number > 0){
			sumOdd += number%10;
			number /= 100;
		}
		return sumOdd;
	}
	
	public static boolean prefixMatched(long number, int d) {
		return false;
	}
	
	public static int getSize(long d)	 {
		return 0;
	}
	
	public static long getPrefix(long number, int k) {
		return 0;
	}
}
