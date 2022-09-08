package chapter_08;

import java.util.Scanner;

public class Ex_14 {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] agrs) {
		System.out.print("Enter the size of the matrix: "); 
		int size = input.nextInt();
		int[][] matrix = new int[size][size];
		randomFillMatrix(matrix);
		Ex_10.printMatrix(matrix);
		searchOnRows(matrix);
		searchOnColumns(matrix);
		searchOnMajorDiagonal(matrix);
		searchOnSubDiagonal(matrix);
	}
	
	public static void randomFillMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (int) (Math.random()*2);
			}
		}
	}
	
	public static void searchOnRows(int[][] matrix) {
		int count = 0; 
		boolean[] found = new boolean[matrix.length]; 
		java.util.Arrays.fill(found, true);
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix.length; column++) {
				if (matrix[row][0] != matrix[row][column]) {
					found[row] = false;
					break;
				}
			}
			if (found[row]) {
				System.out.println("All " + matrix[row][0] + "s on row " + row);
				count++;
			}
		}
		if (count == 0)
			System.out.println("No same numbers on a row");
	}
	
	public static void searchOnColumns(int[][] matrix) {
		int count = 0;
		boolean[] found = new boolean[matrix.length];
		java.util.Arrays.fill(found, true);
		for (int column = 0; column < matrix.length; column++) {
			for (int row = 0; row < matrix.length; row++) {
				if (matrix[row][column] != matrix[0][column]) {
					found[column] = false;
					break;
				}
			}
			if (found[column]) {
				System.out.println("All " + matrix[0][column] + "s on column " + column);
				count++;
			}
		}
		if (count == 0)
			System.out.println("No same numbers on a column");
		
	}
	
	public static void searchOnMajorDiagonal(int[][] matrix) {
		boolean found = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][i] != matrix[0][0]) {
				found = false;
				break;
			}
		}
		if (found) 
			System.out.println("All " + matrix[0][0] + "s on the major diagonal");
		else 
			System.out.println("No same numbers on the major diagonal");
	} 
	
	public static void searchOnSubDiagonal(int[][] matrix) {
		boolean found = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][matrix.length-1-i] != matrix[0][0]) {
				found = false;
				break;
			}
		}
		if (found) 
			System.out.println("All " + matrix[0][0] + "s on the sub-diagonal");
		else 
			System.out.println("No same numbers on the sub-diagonal");
	} 

}
