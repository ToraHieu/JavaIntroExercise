package chapter_06;

public class Ex_17 {
	public static void printMatrix(int n) {
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) 
				System.out.print(Math.round((float)Math.random()) + " ");
			System.out.println();
		}
	}
	
	public static void main (String[] agrs) {
		printMatrix(3);
	}

}
