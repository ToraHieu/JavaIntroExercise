package chapter_08;

public class Ex_02 {
	public static void main(String[] agrs) {
		double[][] m = {{1, 2, 3, 4.0}, 
			            {5, 6.5, 7, 8},
			            {9, 10, 11, 12},
			            {13, 14, 15, 16}};
		System.out.print("Sum of the elements in the major diagonal is " + sumMajorDiagonal(m));
	}
	
	public static double sumMajorDiagonal(double[][] m) {
		double sum = 0;
		for (int i = 0; i < m.length; i++) {
			sum+= m[i][i];
		}
		return sum;
	}

}
