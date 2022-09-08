package chapter_04;
import java.util.Scanner;
public class Ex_11 {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] agrs) {
		System.out.print("Enter a decimal value (0 to 15): ");
		int i = input.nextInt();
		if (i >= 0 && i < 10) {
			System.out.print("The hex value is " + i);
		}
		else if (i >= 10 && i < 16) {
			System.out.print("The hex value is " + (char)(i+'A'-10));
		}
		else 
			System.out.print(i + " is an invalid input");
	}
}
