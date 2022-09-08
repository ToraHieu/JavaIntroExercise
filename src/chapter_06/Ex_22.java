package chapter_06;

public class Ex_22 {
	public static double sqrt(long n) {
		double sqrt = 1;
		double lastGuess = 1, nextGuess = (lastGuess + n / lastGuess)/2;
		while (notSQRT(nextGuess, lastGuess)){
			lastGuess = nextGuess;
			nextGuess = (lastGuess + n / lastGuess)/2;
		}
		sqrt = nextGuess;
		return sqrt;
	}
	
	public static boolean notSQRT (double nextGuess, double lastGuess) {
		if (nextGuess != lastGuess && Math.abs(nextGuess - lastGuess) != 0.0001)
			return true;
		return false;
	}
	
	public static void main (String[] agrs) {
		System.out.println(sqrt(4));
	}

}
