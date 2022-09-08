package chapter_10;

public class Time {
	// Exercise 10.1 
	
	private int hour;
	private int minute;
	private int second;
	
	
	
	Time(long elapseTime) {
		long totalMilliseconds = elapseTime;
		// Obtain the total seconds since midnight, Jan 1, 1970
	    long totalSeconds = totalMilliseconds / 1000;

	    // Compute the current second in the minute in the hour
	    long currentSecond = totalSeconds % 60;
	    
	    // Obtain the total minutes
	    long totalMinutes = totalSeconds / 60;

	    // Compute the current minute in the hour
	    long currentMinute = totalMinutes % 60;

	    // Obtain the total hours
	    long totalHours = totalMinutes / 60;

	    // Compute the current hour
	    long currentHour = totalHours % 24;
		
	    hour = (int) currentHour;
	    minute = (int) currentMinute;
	    second = (int) currentSecond;
	}
	
	Time() {
		this(System.currentTimeMillis());
	}
	
	Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void setTime(long elapseTime) {
		long totalMilliseconds = elapseTime;
		// Obtain the total seconds since midnight, Jan 1, 1970
	    long totalSeconds = totalMilliseconds / 1000;

	    // Compute the current second in the minute in the hour
	    long currentSecond = totalSeconds % 60;
	    
	    // Obtain the total minutes
	    long totalMinutes = totalSeconds / 60;

	    // Compute the current minute in the hour
	    long currentMinute = totalMinutes % 60;

	    // Obtain the total hours
	    long totalHours = totalMinutes / 60;

	    // Compute the current hour
	    long currentHour = totalHours % 24;
		
	    hour = (int) currentHour;
	    minute = (int) currentMinute;
	    second = (int) currentSecond;
	}

}
