package chapter_08;

public class Ex_01 {
	public static void main(String[] agrs) {
		double[][] matrix = {{1.5, 2, 3, 4}, {5.5, 6, 7, 8}, {9.5, 1, 3, 1}};
		for (int i = 0; i < matrix[0].length; i++) {
			double sum = sumColumn(matrix, i);
		System.out.println("Sum of the elements at column " + i + " is "+ sum);
		}
	}
	
	public static double sumColumn(double[][] m, int columnIndex) {
		double sum = 0;
		for (int i = 0; i < m.length; i++) 
			sum+= m[i][columnIndex];
		return sum;
	}

}
