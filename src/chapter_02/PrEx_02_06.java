package chapter_02;

import java.util.*;

public class PrEx_02_06 {
	private static Scanner in;

	public static void main (String[] agrs) {
		in = new Scanner(System.in);
		
		System.out.print("Enter an integer between 0 and 1000: ");
		int remaining = in.nextInt();
		int i1 = remaining % 10;
		remaining /= 10;
		int i2 = remaining % 10;
		remaining /= 10;
		int i3 = remaining % 10;
		
		int sum = i1 + i2 +i3;
		System.out.print(sum);
		
	}

}
