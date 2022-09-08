package chapter_03;

import java.util.*;

public class Ex_05 {
	
	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		int day, nextDay;
		String s1 = null;
		String s2 = null;
		System.out.print("Enter today's day: ");
		day = in.nextInt();
		System.out.print("Enter the number of days elapsed since today: ");
		nextDay = day + in.nextInt();
		
		switch (day) {
		case (0): s1 = "Sunday";
		break;
		case (1): s1 = "Monday";
		break;
		case (2): s1 = "Tuesday";
		break;
		case (3): s1 = "Wednesday";
		break;
		case (4): s1 = "Thursday";
		break;
		case (5): s1 = "Friday";
		break;
		case (6): s1 = "Saturday";
		break;
		}
		
		switch (nextDay % 7) {
		case (0): s2 = "Sunday";
		break;
		case (1): s2 = "Monday";
		break;
		case (2): s2 = "Tuesday";
		break;
		case (3): s2 = "Wednesday";
		break;
		case (4): s2 = "Thursday";
		break;
		case (5): s2 = "Friday";
		break;
		case (6): s2 = "Saturday";
		break;
		}
		
		System.out.printf("Today is %s and the future day is %s",s1,s2);

	}

}
