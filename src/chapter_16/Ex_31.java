package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_31 extends Application {
    private static final int NUMBER_OF_ROWS = 6, NUMBER_OF_COLUMNS = 7;
    private Circle[][] cells = new Circle[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    private int[][] values = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS]; // 0: none | 1: player1 | 2: player2
    private boolean isPlayer1Turn = true;
    private GridPane gridPane;
    private Text status = new Text();
    private boolean isGameOver = false;
    private Timeline animation = new Timeline();
    private int turnCount = 0;
    private Button btnReplay = new Button("Replay");

    @Override
    public void start(Stage primaryStage) {
        status.setText("Player 1's turn");

        gridPane = new GridPane();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                cells[i][j] = new Circle(20);
                cells[i][j].setStroke(Color.BLACK);
                cells[i][j].setFill(Color.WHITE);
                gridPane.add(cells[i][j], j, i);

                cells[i][j].setOnMouseClicked(event -> {
                    if (isGameOver)
                        return;
                    Circle currentCell = (Circle) event.getSource();
                    int rowIndex = GridPane.getRowIndex(currentCell);
                    int columnIndex = GridPane.getColumnIndex(currentCell);

                    if (isAvailableCell(rowIndex, columnIndex)) {
                        turnCount++;

                        if (isPlayer1Turn) {
                            values[rowIndex][columnIndex] = 1;
                            currentCell.setFill(Color.RED);
                            status.setText("Player 2's turn");
                        } else {
                            values[rowIndex][columnIndex] = 2;
                            currentCell.setFill(Color.YELLOW);
                            status.setText("Player 1's turn");
                        }

                        int[][] result = isConsecutiveFour(values);
                        if (result != null) {
                            isGameOver = true;

                            animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
                                cells[result[0][0]][result[0][1]].setFill(Color.WHITE);
                                cells[result[1][0]][result[1][1]].setFill(Color.WHITE);
                                cells[result[2][0]][result[2][1]].setFill(Color.WHITE);
                                cells[result[3][0]][result[3][1]].setFill(Color.WHITE);
                            }),
                                    new KeyFrame(Duration.millis(500), e -> {
                                        Color color = isPlayer1Turn ? Color.RED : Color.YELLOW;
                                        cells[result[0][0]][result[0][1]].setFill(color);
                                        cells[result[1][0]][result[1][1]].setFill(color);
                                        cells[result[2][0]][result[2][1]].setFill(color);
                                        cells[result[3][0]][result[3][1]].setFill(color);
                                    }));
                            animation.setCycleCount(Timeline.INDEFINITE);
                            animation.play();

                            status.setText(isPlayer1Turn ? "Player 1 won!" : "Player 2 won!");
                        } else {
                            if (turnCount == NUMBER_OF_ROWS * NUMBER_OF_COLUMNS) {
                                // It's a draw
                                isGameOver = true;
                                status.setText("It's a draw!");
                            } else {
                                isPlayer1Turn = !isPlayer1Turn;
                            }
                        }
                    }
                });
            }
        }
        gridPane.setHgap(4);
        gridPane.setVgap(4);
        gridPane.setStyle("-fx-background-color: gray");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(5));

        btnReplay.setOnAction(event -> {
            for (int i = 0; i < NUMBER_OF_ROWS; i++) {
                for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                    cells[i][j].setFill(Color.WHITE);

                }
            }
            animation.stop();
            values = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
            turnCount = 0;
            isPlayer1Turn = true;
            status.setText("Player 1's turn");
            isGameOver = false;
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(status);
        borderPane.setBottom(btnReplay);
        BorderPane.setAlignment(status, Pos.CENTER);
        BorderPane.setAlignment(btnReplay, Pos.CENTER);

        Scene scene = new Scene(borderPane, 330, 330);
        primaryStage.setTitle("Ex_31");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isAvailableCell(int rowIndex, int columnIndex) {
        if (values[rowIndex][columnIndex] != 0)
            return false;
        if (rowIndex == NUMBER_OF_ROWS - 1)
            return true;
        return values[rowIndex][columnIndex] == 0 && values[rowIndex+1][columnIndex] != 0;
    }

    /** This method was copied from the solution of Exercise16_30, I'm not really sure how it's implemented but I figured out how to use it.
     * This method returns a two-dimensional array of int that reflect the index/coordination of each element in a consecutive four if found, null otherwise.*/
    public static int[][] isConsecutiveFour(int[][] values) {
        int numberOfRows = values.length;
        int numberOfColumns = values[0].length;

        // Check rows
        for (int i = 0; i < numberOfRows; i++) {
            if (isConsecutiveFour(values[i]) != null) {
                int[][] result = new int[4][2];
                result[0][0] = result[1][0] = result[2][0] = result[3][0] = i;
                int k = isConsecutiveFour(values[i]);

                result[0][1] = k; result[1][1] = k + 1;
                result[2][1] = k + 2; result[3][1] = k + 3;

                return result;
            }
        }

        // Check columns
        for (int j = 0; j < numberOfColumns; j++) {
            int[] column = new int[numberOfRows];
            // Get a column into an array
            for (int i = 0; i < numberOfRows; i++)
                column[i] = values[i][j];

            if (isConsecutiveFour(column) != null) {
                int[][] result = new int[4][2];
                result[0][1] = result[1][1] = result[2][1] = result[3][1] = j;
                int k = isConsecutiveFour(column);

                result[0][0] = k; result[1][0] = k + 1;
                result[2][0] = k + 2; result[3][0] = k + 3;

                return result;
            }
        }

        // Check major diagonal (lower part)
        for (int i = 0; i < numberOfRows - 3; i++) {
            int numberOfElementsInDiagonal
                    = Math.min(numberOfRows - i, numberOfColumns);
            int[] diagonal = new int[numberOfElementsInDiagonal];
            for (int k = 0; k < numberOfElementsInDiagonal; k++)
                diagonal[k] = values[k + i][k];

            if (isConsecutiveFour(diagonal) != null) {
                int[][] result = new int[4][2];
                int k = isConsecutiveFour(diagonal);
                result[0][0] = k + i;
                result[1][0] = k + 1 + i;
                result[2][0] = k + 2 + i;
                result[3][0] = k + 3 + i;
                result[0][1] = k;
                result[1][1] = k + 1;
                result[2][1] = k + 2;
                result[3][1] = k + 3;
                return result;
            }
        }

        // Check major diagonal (upper part)
        for (int j = 1; j < numberOfColumns - 3; j++) {
            int numberOfElementsInDiagonal
                    = Math.min(numberOfColumns - j, numberOfRows);
            int[] diagonal = new int[numberOfElementsInDiagonal];
            for (int k = 0; k < numberOfElementsInDiagonal; k++)
                diagonal[k] = values[k][k + j];

            if (isConsecutiveFour(diagonal) != null) {
                int[][] result = new int[4][2];
                int k = isConsecutiveFour(diagonal);
                result[0][0] = k;
                result[1][0] = k + 1;
                result[2][0] = k + 2;
                result[3][0] = k + 3;
                result[0][1] = k + j;
                result[1][1] = k + 1 + j;
                result[2][1] = k + 2 + j;
                result[3][1] = k + 3 + j;
                return result;
            }
        }

        // Check sub-diagonal (left part)
        for (int j = 3; j < numberOfColumns; j++) {
            int numberOfElementsInDiagonal
                    = Math.min(j + 1, numberOfRows);
            int[] diagonal = new int[numberOfElementsInDiagonal];

            for (int k = 0; k < numberOfElementsInDiagonal; k++)
                diagonal[k] = values[k][j - k];

            if (isConsecutiveFour(diagonal) != null) {
                int[][] result = new int[4][2];
                int k = isConsecutiveFour(diagonal);
                result[0][0] = k;
                result[1][0] = k + 1;
                result[2][0] = k + 2;
                result[3][0] = k + 3;
                result[0][1] = j - k;
                result[1][1] = j - k - 1;
                result[2][1] = j - k - 2;
                result[3][1] = j - k - 3;
                return result;
            }
        }

        // Check sub-diagonal (right part)
        for (int i = 1; i < numberOfRows - 3; i++) {
            int numberOfElementsInDiagonal
                    = Math.min(numberOfRows - i, numberOfColumns);
            int[] diagonal = new int[numberOfElementsInDiagonal];

            for (int k = 0; k < numberOfElementsInDiagonal; k++)
                diagonal[k] = values[k + i][numberOfColumns - k - 1];

            if (isConsecutiveFour(diagonal) != null) {
                int[][] result = new int[4][2];
                int k = isConsecutiveFour(diagonal);
                result[0][0] = k + i;
                result[1][0] = k + i + 1;
                result[2][0] = k + i + 2;
                result[3][0] = k + i + 3;
                result[0][1] = numberOfColumns - k - 1;
                result[1][1] = numberOfColumns - (k + 1) - 1;
                result[2][1] = numberOfColumns - (k + 2) - 1;
                result[3][1] = numberOfColumns - (k + 3) - 1;
                return result;
            }
        }

        return null;
    }

    /** This method was copied from the solution of Exercise16_30.
     * This method returns the index where a consecutive four was first found in an array of int.
     * Returns null if none found.*/
    public static Integer isConsecutiveFour(int[] values) {
        for (int i = 0; i < values.length - 3; i++) {
            if (values[i] == 0)
                continue;
            boolean isEqual = true;
            for (int j = i; j < i + 3; j++) {
                if (values[j] != values[j + 1]) {
                    isEqual = false;
                    break;
                }
            }

            if (isEqual) return i;
        }

        return null;
    }
}
