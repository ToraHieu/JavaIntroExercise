package chapter_05;

public class Ex_07 {
	public static void main (String[] agrs) {
		final double base = 10000;
		double current = base;
		for (int i = 0; i < 10; i++) 
			current *= 1.05;
		double base2 = current;
		double sum = 0, current2 = base2;
		for (int i = 0; i < 4; i++) {
			sum += current2;
			current2 *= 1.05;
		}
		
		System.out.println("The tuition in 10 years is: " + base2);
		System.out.println("The whole tuition cost of 4 years starting 10 years from now is: " + sum);

	}

}
