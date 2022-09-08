package chapter_06;

public class Ex_06 {
	public static void displayPattern(int n) {
		for (int i = 1; i <= n; i++) {
			for (int a = n-1; a >= i; a--) {
				System.out.print("  ");
			}
			for (int b = i; b >= 1; b--) {
				System.out.print(" " + b);
			}
			System.out.println();
		}
	}
	
	public static void main (String[] agrs) {
		int n = 9;
		displayPattern(n);
	}

}
