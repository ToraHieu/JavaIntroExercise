package chapter_09;

public class CkPts_27 {
	public static void main(String[] agrs) {
		java.util.Date[] dates = new java.util.Date[10];
		// Print out "null".
		System.out.println(dates[0]); 
		// An error compile, java.lang.NullPointerException.
		System.out.println(dates[0].toString());
	}

}
