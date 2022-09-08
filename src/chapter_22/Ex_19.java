package chapter_22;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/** Programming Exercise 22.19 (Largest block)
 * The problem for finding a largest block is described in Programming Exercise 8.35.
 * Design a dynamic programming algorithm for solving this problem in O(n2) time.
 * Write a test program that displays a 10-by-10 square matrix, as shown in Figure 22.14a.
 * Each element in the matrix is 0 or 1, randomly generated with a click of the Refresh button.
 * Display each number centered in a text field. Use a text field for each entry.
 * Allow the user to change the entry value.
 * Click the Find Largest Block button to find a largest square submatrix that consists of 1s.
 * Highlight the numbers in the block, as shown in Figure 22.14b.
 *
 * See liveexample.pearsoncmg.com/dsanimation/LargestBlock.html for an interactive test.
  */
public class Ex_19 extends Application {
    private final int numberOfRows = 10, numberOfColumns = 10;
    private final TextField[][] cells = new TextField[numberOfRows][numberOfColumns];
    private final Button btnRefresh = new Button("Refresh"), btnFind = new Button("Find Largest Block");
    private final GridPane mainPane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        // Initialize
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                cells[i][j] = new TextField();
                cells[i][j].setAlignment(Pos.CENTER);
                cells[i][j].setPrefColumnCount(1);
                cells[i][j].setText((int) (Math.random() * 2) + "");

                mainPane.add(cells[i][j], j, i);
            }
        }
        btnRefresh.setOnMouseClicked(event -> refreshCells());
        btnFind.setOnMouseClicked(event -> solve());

        HBox hBox = new HBox(btnRefresh, btnFind);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        BorderPane pane = new BorderPane();
        pane.setCenter(mainPane);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshCells() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                cells[i][j].setText((int) (Math.random() * 2) + "");
                cells[i][j].setStyle("−fx-control-inner-background: white");
            }
        }
    }

    private void solve() {
        // Clear coloring
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                cells[i][j].setStyle("−fx-control-inner-background: white");
            }
        }

        int[] result = getLargestBlock(cells);
        if (result[2] > 0) {
            for (int i = result[0]; i < result[0] + result[2]; i++) {
                for (int j = result[1]; j < result[1] + result[2]; j++) {
                    cells[i][j].setStyle("-fx-control-inner-background: red");
                }
            }
        }
    }

    /** The return value is an array that consists of three values.
     * The first two values are the row and column indices for the first element in the submatrix,
     * and the third value is the number of the rows in the submatrix.*/
    private int[] getLargestBlock(TextField[][] cells) {
        int[] results = new int[3];
        int[][] cellRank = new int[numberOfRows][numberOfColumns];

        for (int i = numberOfRows - 1; i >= 0; i--) {
            for (int j = numberOfColumns - 1; j>= 0; j--) {
                if (cells[i][j].getText().trim().equals("1")) {
                    if (i == numberOfRows - 1 || j == numberOfColumns - 1) {
                        cellRank[i][j] = 1;
                    } else {
                        cellRank[i][j] = 1 + Math.min(cellRank[i + 1][j],
                                Math.min(cellRank[i][j + 1], cellRank[i + 1][j + 1]));
                    }

                    if (cellRank[i][j] > results[2]) {
                        results[0] = i;
                        results[1] = j;
                        results[2] = cellRank[i][j];
                    }
                }
            }
        }

        return results;
    }
}
