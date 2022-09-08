package chapter_05;

public class Ex_05 {
	public static void main (String[] agrs) {
		System.out.println("Kilograms   Pounds   |   Pounds   Kilograms");
	
		int counter, kilo = 1, pound = 20;
		double KPounds, PKilos;
		
		for (counter = 1; counter < 200; counter+=2) {
			KPounds = kilo*2.2;
			PKilos = (double) (pound)*0.45359237d;
			System.out.printf("%-7d   %7.1f    |   %-5d   %5.2f",kilo,KPounds,pound,PKilos);
			System.out.println();
			kilo+=2;
			pound+=5;
		}
	}

}
