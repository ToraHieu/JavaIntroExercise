package chapter_07;

public class Ex_06 {
	public static void main (String[] args) {
		System.out.println("The first 50 prime numbers are :");
		final int numberOfPrimes = 50;
		final int NUMBER_OF_PRIMES_PER_LINE = 10; 
		int count = 0;
		int number = 2;
		int[] primes = new int[50];
		primes[0] = 2;
		for (int i = 1; i < 50; i++) {
			number++;
			while (!isPrime(number, primes)) {
				number++;
			}
			primes[i] = number;
		}
	
		while (count < numberOfPrimes) {
				count++;
				if (count % NUMBER_OF_PRIMES_PER_LINE == 0) 
					System.out.printf("%-4d\n",primes[count-1]);
				else 
					System.out.printf("%-4d",primes[count-1]);
		}
		
	}
	
	public static boolean isPrime(int number,int[] primes) {
		for (int j = 0; primes[j] <= Math.sqrt(number); j++) {
			if (number % primes[j] == 0) {
				return false;
			}
		}
		return true;
	}
	
}
