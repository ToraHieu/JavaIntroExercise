package chapter_06;

public class Ex_01 {
	public static int getPentagonalNumber(int n) {
		int number = n*(3*n-1)/2;
		return number;
	}
	
	public static void main (String[] agrs) {
		final int NUMBERS_PER_LINE = 10;
		final int TOTAL_NUMBERS = 100; 
		
		for (int i = 1; i <= TOTAL_NUMBERS; i++) {
			System.out.printf("%5d ",getPentagonalNumber(i));
			if (i % NUMBERS_PER_LINE == 0) 
				System.out.println();
		}
	}

}
