package chapter_18;

public class Ex_24 {
    // Starts at -1 because the first method call (e.g. putting the Knight into the board) is move 0, not 1.
    private static int moveCount = -1;
    public static void main(String[] args) {
        // Represent the chess board
        boolean[][] board = new boolean[8][8];

        moveKnight(0, 0, board);
        System.out.println("The board is fully accessed with only Knight after " + moveCount + " moves.");
    }

    public static void moveKnight(int row, int column, boolean[][] board) {
        // Check if all the square of board is fully acessed
        if (isFullyAccessed(board)) {
            return;
        }
        board[row][column] = true;
        moveCount++;
        printBoard(board);
        System.out.println("The knight is at [" + row + ", " + column + "]. Move: " + moveCount);

        board[row][column] = true;
        int nextRow, nextColumn;
        // Search for unchecked square, and go to that square then move back to the current square when its queue is exhausted (e.g. No more unchecked square to go).
        if (row + 2 < 8 && column + 1 < 8 && !board[row + 2][column + 1]) {          // Down and go right
            nextRow = row + 2;
            nextColumn = column + 1;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row + 2 < 8 && column - 1 >= 0 && !board[row + 2][column - 1]) {  // Down and go left
            nextRow = row + 2;
            nextColumn = column - 1;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row - 2 >= 0 && column + 1 < 8 && !board[row - 2][column + 1]) {  // Up and go right
            nextRow = row - 2;
            nextColumn = column + 1;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row - 2 >= 0 && column - 1 >= 0 && !board[row - 2][column - 1]) { // Up and go left
            nextRow = row - 2;
            nextColumn = column - 1;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row + 1 < 8 && column - 2 >= 0 && !board[row + 1][column - 2]) {  // Left and go up
            nextRow = row + 1;
            nextColumn = column - 2;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row - 1 >= 0 && column - 2 >= 0 && !board[row - 1][column - 2]) {  // Left and go down
            nextRow = row - 1;
            nextColumn = column - 2;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row - 1 >= 0 && column + 2 < 8 && !board[row - 1][column + 2]) {  // Right and go up
            nextRow = row - 1;
            nextColumn = column + 2;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
        if (row + 1 < 8 && column + 2 < 8 && !board[row + 1][column + 2]) {   // Right and go down
            nextRow = row + 1;
            nextColumn = column + 2;
            moveKnight(nextRow, nextColumn, board);
            moveKnight(row, column, board);
        }
    }

    /** Check if the board is fully accessed */
    private static boolean isFullyAccessed(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean square : row) {
                if (!square)
                    return false;
            }
        }
        return true;
    }

    /** Print the board and which squares have been checked */
    private static void printBoard(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean square : row)
                System.out.print(square ? "X  " : "_  ");
            System.out.println();
        }
    }
}
