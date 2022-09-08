package chapter_08;

import java.util.Scanner;

public class Ex_09 {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		int[][] matrix = new int[3][3];
		int turnCount = 0;
		while (true) {
			printBoard(matrix);
			System.out.print("Enter a row for player X: ");
			int rowX = input.nextInt();
			System.out.print("Enter a column for player X: ");
			int columnX = input.nextInt();
			matrix[rowX][columnX] = 1;
			if (gameOver(matrix, rowX, columnX)) {
				printBoard(matrix);
				System.out.println("X player won");
				break;
			}
			else 
				turnCount++;
			if (turnCount == 9) {
				printBoard(matrix);
				System.out.print("A draw. No winner.");
				break;
			}
			
			printBoard(matrix);
			System.out.print("Enter a row for player O: ");
			int rowO = input.nextInt();
			System.out.print("Enter a column for player O: ");
			int columnO = input.nextInt();
			matrix[rowO][columnO] = 2;
			if (gameOver(matrix, rowO, columnO)) {
				printBoard(matrix);
				System.out.println("O player won");
				break;
			}
			else 
				turnCount++;
			
		}
		
	}
	
	public static boolean gameOver(int[][] matrix, int rowIndex, int columnIndex) {
		// Check if 3 tokens are placed in horizontal.
		boolean caseHorizontal = true;
		for (int i = 0; i < 3; i++) {
			if (matrix[rowIndex][columnIndex] != matrix[rowIndex][i]) {
				caseHorizontal = false;
				break;
			}
		}
		if (caseHorizontal)
			return true;
		
		// Check if 3 tokens are placed in vertical.
		boolean caseVertical = true;
		for (int i = 0; i < 3; i++) {
			if (matrix[rowIndex][columnIndex] != matrix[i][columnIndex]) {
				caseVertical = false;
				break;
			}
		}
		if (caseVertical)
			return true;
		
		// Check if 3 tokens are placed in diagonal. 
		boolean caseDiagonal = true;
		// If the current token is in cells that are impossible to achieve diagonal case.
		if ((rowIndex ==  0 && columnIndex == 1) || (rowIndex == 1 && columnIndex == 0)
				|| (rowIndex == 1 && columnIndex == 2) || (rowIndex == 2 && columnIndex == 1)) 
			return false;
		
		// Check major diagonal case. 
		for (int i = 0; i < 3; i++) {
			if (matrix[i][i] != matrix[rowIndex][columnIndex])
				caseDiagonal = false;
		}
		
		if (caseDiagonal)
			return true;
		
		// Final case, check minor diagonal case.
		if (matrix[rowIndex][columnIndex] == matrix[0][2] && 
				matrix[rowIndex][columnIndex] == matrix[1][1] && 
				matrix[rowIndex][columnIndex] == matrix[2][0])
			return true;
		
		return false;
	}
	
	public static void printBoard(int[][] matrix) {
		System.out.println("-------------");
		System.out.print("|");
		for (int i = 0; i < 3; i++) {
			if (matrix[0][i] == 1)
				System.out.print(" X |");
			else if (matrix[0][i] == 2)
				System.out.print(" O |");
			else 
				System.out.print("   |");
		}
		System.out.println();
		System.out.println("-------------");
		System.out.print("|");
		for (int i = 0; i < 3; i++) {
			if (matrix[1][i] == 1)
				System.out.print(" X |");
			else if (matrix[1][i] == 2)
				System.out.print(" O |");
			else 
				System.out.print("   |");
		}
		System.out.println();
		System.out.println("-------------");
		System.out.print("|");
		for (int i = 0; i < 3; i++) {
			if (matrix[2][i] == 1)
				System.out.print(" X |");
			else if (matrix[2][i] == 2)
				System.out.print(" O |");
			else 
				System.out.print("   |");
		}
		System.out.println();
		System.out.println("-------------");
	}

}
