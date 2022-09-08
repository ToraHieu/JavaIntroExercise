package chapter_07;

public class Ex_13 {
	public static void main(String[] agrs) {
		int[] numbers = new int [10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i+1;
		}
		System.out.print("The random number excluding 1 to 50 is: " + getRandom(numbers));
	}
	
	public static int getRandom(int... numbers) {
		int random = (int) (Math.random()*54 + 1);
		if (isLegit(random,numbers)) 
			return random;
		else 
			return getRandom(numbers);
		
	}
	
	public static boolean isLegit(int number, int... numbers) {
		for (int i = 0; i < numbers.length; i++) {
			if (number == numbers[i]) {
				return false;
			}
		}
		return true;
	}
}
