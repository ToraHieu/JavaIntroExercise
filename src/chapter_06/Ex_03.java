package chapter_06;

public class Ex_03 {
	public static int reverse (int number) {
		int reversed = 0;
		while (number != 0) {
		reversed = reversed*10 + number%10;
		number /= 10;
		}
		return reversed;
	}
	
	public static boolean isPalindrome(int number) {
		if (reverse(number) == number)
			return true;
		else 
			return false;
	}
	
	public static void main (String[] agrs) {
		int number = 123454321;
		System.out.println("Reversed: " + reverse(number));
		System.out.printf("Is %d a Palindrome number: %b",number,isPalindrome(number));
		
	}
}
