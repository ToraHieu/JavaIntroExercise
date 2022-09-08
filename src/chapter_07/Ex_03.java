package chapter_07;

import java.util.Scanner;

public class Ex_03 {
	private static final Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		int[] numbers = new int[100];
		System.out.print("Enter the integers between 1 and 100: ");
		int number = in.nextInt();
		while (number != 0) {
			numbers[number-1]++;
			number = in.nextInt();
		}
		
		for (int i = 0; i < 100; i++) {
			if (numbers[i] == 1) 
				System.out.print(i+1 + " occurs 1 time\n");
			else if (numbers[i] > 1)
				System.out.printf("%d occurs %d times\n", i+1, numbers[i]);
		}
		
	}

}
