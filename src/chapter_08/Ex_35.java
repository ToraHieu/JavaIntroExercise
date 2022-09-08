package chapter_08;
import java.util.Scanner;
public class Ex_35 {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Enter the number of rows in the matrix: ");
		int size = input.nextInt();
		int[][] matrix = new int[size][size];
		System.out.println("Enter the matrix row by row: ");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = input.nextInt();
			}
		}
		int[] result = findLargestBlock(matrix);
		if (result != null)
			System.out.println("The maximum square submatrix is at (" + result[0] + ", " + result[1] +") with size " + result[2]);
		
		/* For convenient, a sample matrix is prepared here:
		 1 0 1 0 1
		 1 1 1 0 1
		 1 0 1 1 1
		 1 0 1 1 1
		 1 0 1 1 1
		 * The correct output for this matrix should be "at (2, 2) with size 3"
		 */
	}
	
	public static int[] findLargestBlock(int[][] m) {
		int currentSize;
		int rowIndex, columnIndex;
		for (currentSize = m.length; currentSize >= 0; currentSize--) {
			for (int i = 0; i < m.length && i + currentSize -1 < m.length; i++) {
				for (int j = 0; j < m[i].length && j + currentSize -1 < m.length; j++) {
					int[][] subMatrix = subMatrix(m, i, j, currentSize);
					if (isMaximumBlock(subMatrix)) {
						rowIndex = i;
						columnIndex = j;
						return new int[] {rowIndex, columnIndex, currentSize};
					}
				}
			}
		}
		
		return null;
	}
	
	public static boolean isMaximumBlock (int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 0) 
					return false;
			}
		}
		return true;
	}
	
	public static int[][] subMatrix (int[][] source, int rowIndex, int columnIndex, int size) {
		int[][] subMatrix = new int[size][size]; 
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				subMatrix[i][j] = source[rowIndex+i][columnIndex+j];
			}
		}
		
		return subMatrix;
	}
}