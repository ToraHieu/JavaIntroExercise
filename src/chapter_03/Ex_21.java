package chapter_03;

import java.util.Scanner;

public class Ex_21 {
	private static Scanner in = new Scanner (System.in);
	
	public static void main (String[] agrs) {
		System.out.print("Enter year: ");
		int year = in.nextInt();
		
		System.out.print("Enter month: ");
		int month = in.nextInt();
		
		System.out.print("Enter the day of the month: ");
		int day = in.nextInt();
		
		if (month <3) {
			month += 12;
			year--;
		}
		
		int k = year%100;
		int j = year/100;
		int h; 
		h = (day + (26*(month + 1)/10) + k + k/4 + j/4 + 5*j) % 7;
		
		String Day = "";
		switch (h) {
		case 0: 
			Day = "Saturday"; break;
		case 1: 
			Day = "Sunday"; break;
		case 2: 
			Day = "Monday"; break;
		case 3: 
			Day = "Tuesday"; break;
		case 4: 
			Day = "Wednesday"; break;
		case 5: 
			Day = "Thursday"; break;
		case 6:
			Day = "Friday"; 
		}
		
		System.out.print("Day of the week is " + Day);
	}

}
