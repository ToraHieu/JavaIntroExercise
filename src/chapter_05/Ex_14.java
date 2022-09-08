package chapter_05;

import java.util.*;

public class Ex_14 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.println("Enter two positive numbers: ");
		int n1 = in.nextInt();
		int n2 = in.nextInt();
		int d = 1, gcd = 1;
		
		if (n1 < n2) 
			d = n1;
		else 
			d = n2;
		
		for (int i = 0; i < d; i++) {
			int temp = d-i;
			if (n1 % temp == 0 && n2 % temp ==0) {
				gcd = temp;
				break;
			}
		}
		
		System.out.println("The GCD is: " + gcd);
	}
}
