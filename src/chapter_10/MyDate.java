package chapter_10;

import java.util.GregorianCalendar;

public class MyDate {
    private int year;
    private int month; // month is 0-based.
    private int day;
    
    public MyDate() {
        GregorianCalendar today = new GregorianCalendar();
        year = today.get(GregorianCalendar.YEAR);
        month = today.get(GregorianCalendar.MONTH);
        day = today.get(GregorianCalendar.DATE);
    }
    
    public MyDate(long elapsedTime) {
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(elapsedTime);
        year = date.get(GregorianCalendar.YEAR);
        month = date.get(GregorianCalendar.MONTH);
        day = date.get(GregorianCalendar.DATE);
    }
    
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setDate(long elapsedTime) {
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(elapsedTime);
        year = date.get(GregorianCalendar.YEAR);
        month = date.get(GregorianCalendar.MONTH);
        day = date.get(GregorianCalendar.DATE);
    }

}
