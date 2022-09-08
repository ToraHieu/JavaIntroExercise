package chapter_07;

public class Ex_14 {
	public static void main(String[] agrs) {
		int[] numbers = {25, 100, 15, 9, 7, 8};
		int gcd = gcd(numbers);
		System.out.print(gcd);
	}
	
	public static int gcd(int... numbers) {
		int gcd = 1;
		int current = min(numbers);
		while (!gcdFound(current, numbers) && current > 1) {
			current--;
		}
		gcd = current;
		return gcd;
	}
	
	public static int min(int... numbers) {
		int min = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (min > numbers[i]) 
				min = numbers[i];
		}
		
		return min;
	}
	
	public static boolean gcdFound(int current, int... numbers) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % current != 0) {
				return false;
			}
		}
		return true;
	}

}
