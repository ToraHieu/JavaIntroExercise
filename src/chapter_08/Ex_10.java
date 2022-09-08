package chapter_08;

public class Ex_10 {
	public static void main(String[] agrs) {
		int[][] matrix = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j] = (int) (Math.random()*2);
			}
		}
		printMatrix(matrix);
		System.out.println("The largest row index: " + largestRow(matrix));
		System.out.println("The largest column index: " + largestColumn(matrix));

	}
	
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int largestRow(int[][] matrix) {
		int largest = 0;
		int largestIndex = 0;
		for (int row = 0; row < matrix.length; row++) {
			int currentSum = 0;
			for (int column = 0; column < matrix[row].length; column++) {
				currentSum += matrix[row][column];
			}
			if (largest < currentSum) {
				largest = currentSum;
				largestIndex = row;
			}
		}
		return largestIndex;
	}
	
	public static int largestColumn(int[][] matrix) {
		int largest = 0;
		int largestIndex = 0;
		for (int column = 0; column < matrix[0].length; column++) {
			int currentSum = 0;
			for (int row = 0; row < matrix.length; row++) {
				currentSum+= matrix[row][column];
			}
			if (largest < currentSum) {
				largest = currentSum;
				largestIndex = column;
			}
		}
		return largestIndex;
	}

}
