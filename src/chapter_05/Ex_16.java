package chapter_05;

import java.util.*;

public class Ex_16 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter a number: ");
		int i = in.nextInt();
		System.out.print("All the factors of " + i + " are: ");
		int temp = i, j = 2;
		while (temp > 1) {
			if (temp % j == 0) {
				System.out.print(j + " ");
				temp /= j;
			}
			else 
				j++;
			
		}
	
	}

}
