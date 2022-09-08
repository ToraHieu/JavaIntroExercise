package chapter_08;

public class Ex_19 {
	public static void main(String[] agrs) {
		int[][] values = {{0, 1, 0, 3, 1, 6, 1},
				          {0, 1, 6, 8, 6, 0, 1}, 
				          {5, 6, 2, 0, 8, 2, 9},
				          {6, 5, 3, 1, 7, 9, 1},
				          {1, 3, 6, 3, 4, 0, 7},
				          {0, 0, 0, 0, 4, 0, 7}};
		if (Ex_20.isConsecutiveFour(values))
			System.out.println("The array contains consecutive fours");
		else 
			System.out.println("The array doesn't contain consecutive fours");
		
	}
	
	public static boolean isConsecutiveFour(int[][] values) {
		// Check  vertically.
		for (int row = 0; row < values.length; row++) {
			for (int column = 0; column + 3 < values[row].length; column++) {
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
		
		// Check major diagonally (\)
		for (int row = 0; row+3 < values.length; row++) {
			for (int column = 0; column+3 < values[row].length; column++) {
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
		
		// Check minor diagonally (/)
		for (int row = 0; row+3 < values.length; row++) {
			for (int column = values.length-1; column-3 >= 0; column--) {
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
