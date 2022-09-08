package chapter_13;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ex_04 {
    /** Main method */
    public static void main(String[] args) {
        int month, year;
        if (args.length == 2) {
            month = Integer.parseInt(args[0]);
            year = Integer.parseInt(args[1]);
        } else if (args.length == 1) {
            month = Integer.parseInt(args[0]);
            year = new GregorianCalendar().get(Calendar.YEAR);
        } else {
            GregorianCalendar today = new GregorianCalendar();
            month = today.get(Calendar.MONTH);
            year = today.get(Calendar.YEAR);
        }
        
        // Create a GregorianCalendar object with the first day of the month and year from input.
        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        
        // Print calendar for the month of the year
        printMonth(calendar);
    }

    /** Print the calendar for a month in a year */
    public static void printMonth(GregorianCalendar calendar) {
        // Print the headings of the calendar
        printMonthTitle(calendar);

        // Print the body of the calendar
        printMonthBody(calendar);
    }

    /** Print the month title, e.g., May, 1999 */
    public static void printMonthTitle(GregorianCalendar calendar) {
        System.out.println("         " + getMonthName(calendar.get(Calendar.MONTH) + 1) + " " + calendar.get(Calendar.YEAR));
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    /** Get the English name for the month */
    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
        case 1:
            monthName = "January";
            break;
        case 2:
            monthName = "February";
            break;
        case 3:
            monthName = "March";
            break;
        case 4:
            monthName = "April";
            break;
        case 5:
            monthName = "May";
            break;
        case 6:
            monthName = "June";
            break;
        case 7:
            monthName = "July";
            break;
        case 8:
            monthName = "August";
            break;
        case 9:
            monthName = "September";
            break;
        case 10:
            monthName = "October";
            break;
        case 11:
            monthName = "November";
            break;
        case 12:
            monthName = "December";
        }
        
        return monthName;
    }

    /** Print month body */
    public static void printMonthBody(GregorianCalendar calendar) {
        // Get start day of the week for the first date in the month
        // with 0 for Sunday
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Get number of days in the month
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DATE);

        // Pad space before the first day of the month
        int i = 0;
        for (i = 0; i < startDay; i++)
            System.out.print("    ");

        for (i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);

            if ((i + startDay) % 7 == 0)
                System.out.println();
        }

        System.out.println();
    }
}
