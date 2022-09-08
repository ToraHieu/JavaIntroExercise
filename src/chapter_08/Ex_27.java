package chapter_08;

public class Ex_27 {
	public static void main(String[] agrs) {
		double[][] matrix = {{0.15, 0.875, 0.375}, 
				             {0.55, 0.005, 0.225},
				             {0.30, 0.12, 0.4}};
		double[][] sorted = sortColumns(matrix);
		printMatrix(sorted);
	}
	
	public static double[][] sortColumns(double[][] m) {
		double[][] sorted = new double[m.length][m.length];
		System.arraycopy(m, 0, sorted, 0, m.length);
		for (int column = 0; column < sorted.length; column++) {
			double[] tempColumn = new double[sorted.length];
			for (int row = 0; row < sorted.length; row++) {
				tempColumn[row] = sorted[row][column];
			}
			java.util.Arrays.sort(tempColumn);
			for (int row = 0; row < sorted.length; row++) {
				sorted[row][column] = tempColumn[row];
			}		
		}
		
		return sorted;
	}
	
	public static void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%3.3f ",matrix[i][j]);
			}
			System.out.println();
		}
	}

}
