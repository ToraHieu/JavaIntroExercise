package chapter_05;

import java.util.*;

public class Ex_01 {
	private static Scanner in;
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		int total = 0, positive = 0, negative = 0, number, sum = 0;
		float average;
		
		System.out.print("Enter an integer, the inputs ends if it is 0:");
		number = in.nextInt();
		
		while (number != 0) {
			total++;
			if (number > 0)
				positive++;
			else 
				negative++;
			sum += number;
			number = in.nextInt();
		}
		
		if (total == 0) {
			System.out.println("No numbers are entered except 0");
			
		}
		else {
			total++;
			average = (float) sum/(total-1);
			System.out.printf(
					  "The number of positives is %d \n"
					+ "The number of negatives is %d \n"
					+ "The total is %d \n"
					+ "The average is %1.2f",positive,negative,total,average);
		}
		
	}

}
