package chapter_06;

public class Ex_24 {

	public static void main (String[] agrs ) {
		
		long totalMils = System.currentTimeMillis();
		long totalSec = totalMils/1000;
		long currentSec = totalSec%60;
		long totalMin = totalSec/60;
		long currentMin = totalMin%60;
		long totalHour = totalMin/60;
		long currentHour = totalHour%24;
		long totalDays = totalHour/24+1;
		
		int Year = 1970;
		while (totalDays >= 365) {
			if(totalDays == 365 && isLeapYear(Year))
				break;
			if (isLeapYear(Year)) 
				totalDays -= 366;
			else 
				totalDays -= 365;
			Year++;
		}
		
		int leftDays = (int) totalDays;
		int month = 1;
		for (int i = 1; i < 12; i++) {
			if(didPassMonth(i, leftDays, Year)) {
				leftDays -= daysInMonth(i, Year);
				month++;
			}
			else 
				break;
		}
		
		String monthName = getMonthName(month);
		System.out.println("Today date and time is " + monthName + " " + leftDays + ", " + Year + " " + currentHour + ":" + currentMin +":" + currentSec);
	}
	
	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 & year % 100 != 0);
	}
	
	public static String getMonthName (int month) {
		String name = "";
		switch (month) {
		case 1: 
			name = "Jan";
			break;
		case 2: 
			name = "Feb";
			break;
		case 3:
			name = "Mar";
			break;
		case 4: 
			name = "Apr";
			break;
		case 5: 
			name = "May";
			break;
		case 6:
			name = "Jun";
			break;
		case 7:
			name = "Jul";
			break;
		case 8: 
			name = "Aug";
			break;
		case 9: 
			name = "Sep";
			break;
		case 10: 
			name = "Oct";
			break;
		case 11: 
			name = "Nov";
			break;
		case 12: 
			name = "Dec";
			
		}
		return name;
	}
	public static boolean didPassMonth(int month, int leftDays, int Year) {
			if (leftDays >= daysInMonth(month, Year)) 
				return true;
		return false;
	}
	
	public static int daysInMonth(int month, int Year) {
		int days = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: 
			 days = 31;
			break;
		case 2:
			if (isLeapYear (Year))
				days = 29;
			else
				days = 28;
			break;
			default: 
				days = 30;
		}
		return days;
	}
}
