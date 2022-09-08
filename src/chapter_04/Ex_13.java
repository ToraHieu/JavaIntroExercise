package chapter_04;
import java.util.Scanner;
public class Ex_13 {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Enter a letter: ");
		String s = input.next();
		char ch = s.charAt(0);
		if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
			s = ch + "";
			s = s.toLowerCase();
			switch (s) {
			case ("a"): 
			case ("e"):
			case ("u"):
			case ("i"):
			case ("o"): {
				System.out.print(ch + " is a vowel");
				break;
			}
			default: 
				System.out.print(ch + " is a consonant");
			}
		}
		else 
			System.out.print(ch + " is an invalid input");
	}
	
}
