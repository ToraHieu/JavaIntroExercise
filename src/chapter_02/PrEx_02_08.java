package chapter_02;
import java.util.*;

public class PrEx_02_08 {
	private static Scanner in;

	public static void main (String[] agrs ) {
		in = new Scanner(System.in);
		
		long totalMils = System.currentTimeMillis();
		long totalSec = totalMils/1000;
		long currentSec = totalSec%60;
		long totalMin = totalSec/60;
		long currentMin = totalMin%60;
		long totalHour = totalMin/60;
		long currentHour = totalHour%24;
		
		System.out.print("Enter the timezone offset to GMT: ");
		long offset = in.nextLong();
		System.out.println("The current " + (currentHour + offset) + ":" + currentMin + ":" + currentSec +".");
		
	}

}
