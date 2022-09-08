package chapter_03;

import java.util.*;

public class Ex_11 {
	
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner(System.in);
		
		System.out.print("Enter month: ");
		int monthNum = in.nextInt();
		System.out.print("Enter the year: ");
		int year = in.nextInt();
		int monthDays;
		String monthName;
		
		switch (monthNum) {
		case 1:
			monthDays = 31;
			monthName = "January";
			break;
		case 2: 
			monthName = "February";
			if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) 
				monthDays = 29;
			else 
				monthDays = 28;
		break;
		case 3:
			monthDays = 31;
			monthName = "March";
			break;
		case 4:
			monthDays = 30;
			monthName = "April";
			break;	
		case 5:
			monthDays = 31;
			monthName = "May";
			break;
		case 6:
			monthDays = 30;
			monthName = "June";
			break;
		case 7:
			monthDays = 31;
			monthName = "July";
			break;
		case 8:
			monthDays = 31;
			monthName = "August";
			break;
		case 9:
			monthDays = 30;
			monthName = "September";
			break;
		case 10:
			monthDays = 31;
			monthName = "October";
			break;
		case 11:
			monthDays = 30;
			monthName = "November";
			break;
		case 12:
			monthDays = 31;
			monthName = "December";
			break;
		default:
				System.out.println("Error input!");
				monthName = "Error";
				year = 0;
				monthDays = 0;
		}

		
		System.out.printf("%s %d has %d days", monthName, year, monthDays);
		
		
	}
}
