package chapter_09;
import java.util.GregorianCalendar;
public class Ex_05 {
	public void displayDate(GregorianCalendar date) {
		System.out.println(date.get(GregorianCalendar.YEAR) + " " + (date.get(GregorianCalendar.MONTH)+1) + " " + date.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	public static void main(String[] agrs) {
		GregorianCalendar date = new GregorianCalendar();
		Ex_05 test = new Ex_05();
		test.displayDate(date);
		
		date.setTimeInMillis(1234567898765L);
		test.displayDate(date);
	}
}
