package chapter_05;

public class Ex_23 {
	public static void main (String[] agrs) {
		double sumLeft = 0, sumRight = 0;
		
		for (int i = 1; i <= 50000; i++) {
			sumLeft+= 1.0/i;
		}
		
		for (int i = 50000; i > 0; i--) {
			sumRight+= 1.0/i;
		}
		
		System.out.println("Sum Left = " + sumLeft);
		System.out.println("Sum Right = " + sumRight);

	}

}
