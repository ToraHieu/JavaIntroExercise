package chapter_08;

public class Ex_06 {
	public static double[][] multiplyMatrix(double[][] a, double[][] b) {
		double[][] c = new double[a.length][b[0].length];
		int n = b.length;
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				double sum = 0.0;
				for (int k = 0; k < n; k++) {
					sum+= a[i][k] * b[k][j]; 
				}
				c[i][j] = sum;
			}
		}
		return c;
	}
	
	public static void main(String[] agrs) {
		double[][] a = {{1, 2, 3},
				        {4, 5, 6},
				        {7, 8, 9}};

		double[][] b = {{0, 2.0, 4.0},
				        {1, 4.5, 2.2},
				        {1.1, 4.3, 5.2}};

		double[][] c = multiplyMatrix(a, b);
		
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
