package chapter_08;
import java.util.Scanner;
public class Ex_20 {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		int[][] board = new int[6][7];
		boolean gameContinue = true;
		int turnCount = 0;
		while (gameContinue) {
			// 1 for Red, 2 for Yellow
			int currentPlayer = (turnCount%2 == 0) ? 1 : 2;
			String player = (currentPlayer == 1) ? "red" : "yellow";
			printBoard(board);
			System.out.print("Drop a " + player + " disk at column (0-6): ");
			int column = input.nextInt();
			dropDisk(board, currentPlayer, column);
			if (isConsecutiveFour(board)) {
				printBoard(board);
				System.out.print("The " + player + " player won");
				break;
			}
			else if (turnCount == 42 ){
				printBoard(board);
				System.out.print("Draw!");
				break;
			}
			else {
				System.out.println("The game continue.");
				turnCount++;
			}
		}
	}
	
	public static void printBoard(int[][] board) {
		// None = 0 (default), Red = 1, Yellow = 2.
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int column = 0; column < board[row].length; column++) {
				if (board[row][column] == 1) 
					System.out.print("R|");
				else if (board[row][column] == 2) 
					System.out.print("Y|");
				else 
					System.out.print(" |");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
	
	public static void dropDisk(int[][] board, int currentPlayer, int column) {
		int currentRow;
		for (currentRow = board.length-1; currentRow >= 0; currentRow--) {
			if (board[currentRow][column] > 0) {
				continue;
			}
			else {
				board[currentRow][column] = currentPlayer;
				return;
			}
		}
		
	}
	
	public static boolean isConsecutiveFour(int[][] values) {
		
		// Check  vertically.
		for (int row = 0; row < values.length; row++) {
			for (int column = 0; column + 3 < values[row].length; column++) {
				if (values[row][column] == 0) {
					continue;
				}
				boolean found = true;
				for (int i = column; i < column+3; i++) {
					if (values[row][i] != values[row][i+1]) {
						found = false;
						break;
					}
				}
				if (found)
					return true;
			}
		}
		
		// Check horizontally
		for (int column = 0; column < values[0].length; column++) {
			for (int row = 0; row + 3 < values.length; row++) {
				if (values[row][column] == 0) {
					continue;
				}
				boolean found = true;
				for (int i = row; i < row+3; i++) {
					if (values[i][column] != values[i+1][column]) {
						found = false;
						break;
					}
				}
				if (found)
					return true;
			}
		}
		
		// Check major diagonally 
		for (int row = 0; row+3 < values.length; row++) {
			for (int column = 0; column+3 < values[row].length; column++) {
				if (values[row][column] == 0) {
					continue;
				}
				boolean found = true;
				for (int i = 1; i < 4; i++) {
					if (values[row][column] != values[row+i][column+i]) {
						found = false;
						break;
					}
				}
				if (found)
					return true;
			}
		}
		
		// Check minor diagonally
		for (int row = 0; row+3 < values.length; row++) {
			for (int column = values.length-1; column-3 >= 0; column--) {
				if (values[row][column] == 0) {
					continue;
				}
				boolean found = true;
				for (int i = 1; i < 4; i++) {
					if (values[row][column] != values[row+i][column-i]) {
						found = false;
						break;
					}
				}
				if (found)
					return true;
			}
		}
		
		return false;
	}
	
}
