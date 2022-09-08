package chapter_18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_34 extends Application {
    private static final int SIZE = 8;
    @Override
    public void start(Stage primaryStage) {
        QueenBoard queenBoard = new QueenBoard();
        Scene scene = new Scene(queenBoard, 200, 200);
        primaryStage.setTitle("Ex_34");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Set one initial queen(s) on the board
//        queenBoard.board[0][5] = true;
        boolean[][] reservedBoard = queenBoard.board.clone();
        if (!solve(queenBoard.board, 0))
            queenBoard.board = reservedBoard;
        queenBoard.paint();
    }

    // Backtracking technique
    private boolean solve(boolean[][] board, int row) {
        if (!isValid(board))
            return false;

        // The last queen is placed correctly.
        if (row == SIZE)
            return true;

        // Recursively Backtracking
        for (int column = 0; column < SIZE; column++) {
            board[row][column] = true;
            if (solve(board, row + 1))
                return true;
            board[row][column] = false;
        }

        return false;        // This code is reached only when the first queen(s) is placed so that it can't be solved.
    }

    /** Check if the current queens are valid */
    private boolean isValid(boolean[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j]) {
                    if (!isValid(board, i, j))
                        return false;
                }
            }
        }
        return true;
    }

    /** Check if the queen in this square is valid */
    private boolean isValid(boolean[][] board, int row, int column) {
        /* Because queens are placed from top to bottom (row 0 to 7),
        we only need to check the upper directions */
        // Check upper left Diagonal: \
        for (int i = 1; i <= column && i <= row; i++)
            if (board[row - i][column - i])
                return false;

        // Check upper Vertical: |
        for (int i = 0; i < row; i++) {
            if (board[i][column])
                return false;
        }

        // Check upper right Diagonal: /
        for (int i = 1; i  + column < SIZE && i <= row; i++)
            if (board[row - i][column + i])
                return false;

        /* Legacy code used for checking in all directions */
//        // Check right Horizontal: ––
//        for (int i = column + 1; i < SIZE; i++)
//            if (board[row][i])
//                return false;
//
//        // Check lower right Diagonal: \
//        for (int i = 1; i + row < SIZE && i + column < SIZE ;i++)
//            if (board[row + i][column + i])
//                return true;
//
//        // Check lower Vertical: |
//        for (int i = row + 1; i < SIZE; i++)
//            if (board[i][column])
//                return false;
//
//        // Check lower left Diagonal: /
//        for (int i = 1; row + i < SIZE && i < column; i++)
//            if (board[row + i][column - i])
//                return false;
//
//        // Check left Horizontal: ––
//        for (int i = 0; i < column; i++)
//            if (board[row][i])
//                return false;

        return true;
    }

    static class QueenBoard extends Pane {
        // Represent the board, true value means occupied by a Queen
        boolean[][] board = new boolean[SIZE][SIZE];

        QueenBoard() {
            paint();
        }

        void paint() {
            // Clear previous drawing
            this.getChildren().clear();

            // Draw grid lines
            for (int i = 1; i <= SIZE; i++) {
                this.getChildren().add(
                        new Line(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE));
                this.getChildren().add(
                        new Line(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight()));
            }

            // Draw the queens
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j]) {
                        ImageView queen = new ImageView("image/queen.jpg");
                        // Add the Queen image
                        this.getChildren().add(queen);
                        // Set the coordinates and sizes
                        queen.setX(j * getWidth() / SIZE);
                        queen.setY(i * getHeight() / SIZE);
                        queen.setFitWidth(getWidth() / SIZE);
                        queen.setFitHeight(getHeight() / SIZE);
                    }
                }
            }
        }
    }
}
