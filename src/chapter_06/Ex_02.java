package chapter_06;

public class Ex_02 {
	public static int sumDigits(long n) {
		int sum = 0;
		long abs = Math.abs(n);
		while (abs > 0) {
			int Digit = (int) (abs % 10);
			sum += Digit;
			abs/= 10;
		}
		return sum;
	}
	
	public static void main (String[] args) {
		System.out.println(sumDigits(-963));
	}

}
