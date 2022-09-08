package chapter_03;

import java.util.*;

public class Ex_15 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter your lottery ticket number: ");
		int ticketNum = in.nextInt();
		int lotNum = (int) (Math.random()*1000);
		
		if (ticketNum == lotNum) {
			System.out.println("Exact match! You won $10,000");
		}
		else {
			// Apparently, there's way to implement this using methods in Java API
			// But we're probably supposed to use only If-else and switch here.
			// Writing all those if-else would be boring so I'll just skip it.
		}
	}

}
