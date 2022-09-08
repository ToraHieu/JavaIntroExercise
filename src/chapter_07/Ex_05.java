package chapter_07;
import java.util.Scanner;
public class Ex_05 {
	private static Scanner in = new Scanner(System.in);
	public static void main (String[] args) {
		boolean[] notDistinct = new boolean[10];
		int[] numbers = new int[10];
		System.out.print("Enter ten numbers: ");
		for (int i = 0; i < 10; i++) {
			int current = in.nextInt();
			for (int j = 0; j < i; j++) {
				if (current == numbers[j]) {
					notDistinct[i] = true;
					break;
				}
			}
			if (!notDistinct[i]) {
				numbers[i] = current;
			}
			
		}
		System.out.print("The distinct numbers are: ");
		for (int i = 0; i < 10; i++) {
			if (!notDistinct[i])
				System.out.print(numbers[i] + " ");
		}
	}
}
