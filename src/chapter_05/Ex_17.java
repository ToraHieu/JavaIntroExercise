package chapter_05;

import java.util.*;

public class Ex_17 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter the number of lines: ");
		int line = in.nextInt();
		
		//Starting loops. 1 line per iteration. 
		for (int i = 1; i <= line; i++) {
			//Print the blank spaces before the numbers on the left. 
			for (int a = line-i; a > 0; a--) {
				System.out.print("  ");
			}
			
			//Print the numbers on the left
			for (int b = i; b > 1; b--) {
				System.out.print(b + " ");
			}
			
			//Print the number 1. (Axis of symmetry: Trục đối xứng)
			System.out.print("1");
			
			//Print the number on the right.
			for (int c = 2; c <= i; c++) {
				System.out.print(" " + c);
			}
			
			//Print the blank spaces after the numbers on the right. 
			for (int d = line-i; d > 0; d--) {
				System.out.print("  ");
			}
			//Go down the line before ending iteration.
			System.out.println();
		}
		
	}

}
