package chapter_08;

public class Ex_22 {
	public static void main(String[] agrs) {
		int[][] matrix = new int[6][6];
		Ex_14.randomFillMatrix(matrix);
		Ex_10.printMatrix(matrix);
		if (isAllEven1s(matrix)) 
			System.out.println(true);
		else
			System.out.println(false);

	}
	
	public static boolean isAllEven1s(int[][] matrix) {
		// Checking rows
		for (int row = 0; row < matrix.length; row++) {
			int currentSum = 0;
			for (int column = 0; column < matrix[row].length; column++) {
				currentSum+= matrix[row][column];
			}
			if (currentSum % 2 != 0) 
				return false;
		}
		
		// Checking columns
		for (int column = 0; column < matrix[0].length; column++) {
			int currentSum = 0;
			for (int row = 0; row < matrix.length; row++) {
				currentSum+= matrix[row][column];
			}
			if (currentSum % 2 != 0) 
				return false;
		}
		
		return true;
	}

}
