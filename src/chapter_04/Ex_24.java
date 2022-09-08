package chapter_04;
import java.util.Scanner;
public class Ex_24 {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] agrs) {
		System.out.print("Enter the first city: ");
		String city1 = input.nextLine();
		System.out.print("Enter the second city: ");
		String city2 = input.nextLine();
		System.out.print("Enter the third city: ");
		String city3 = input.nextLine();
		
		if (city1.compareTo(city2) > 0) {
			String temp = city1;
			city1 = city2;
			city2 = temp;
		}
		
		if (city2.compareTo(city3) > 0) {
			String temp = city2;
			city2 = city3;
			city3 = temp;
		}
		
		if (city1.compareTo(city2) > 0) {
			String temp = city1;
			city1 = city2;
			city2 = temp;
		}
		
		System.out.print("The three cities in alphabetical order are " + city1 + " " + city2 + " " + city3);
	}
}
