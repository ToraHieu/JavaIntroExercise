package chapter_02;

public class DisplayCurrentTime {
	public static void main (String[] agrs ) {
		long totalMils = System.currentTimeMillis();
		long totalSec = totalMils/1000;
		long currentSec = totalSec%60;
		long totalMin = totalSec/60;
		long currentMin = totalMin%60;
		long totalHour = totalMin/60;
		long currentHour = totalHour%24;
		
		System.out.println("It's " + currentHour + ":" + currentMin + ":" + currentSec +" in GMT");
		
	}

}
