package chapter_18;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_26 extends Application {
//    private static int count = 0; // Debug purpose
    private static final int rowCount = 9, columnCount = 9;
    private Label lblStatus = new Label("Set up and click Find Path");

    private Cell[][] cells = new Cell[rowCount][columnCount];

    @Override
    public void start(Stage primaryStage) {
        GridPane cellPane = new GridPane();
        for (int row = 0; row < rowCount; row++)
            for (int column = 0; column < columnCount; column++)
                cellPane.add(cells[row][column] = new Cell(), column, row);
        cellPane.setGridLinesVisible(true);

        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();
        Button btnFindPath = new Button("Find Path");
        Button btnClearPath = new Button("Clear Path");
        hBox.getChildren().addAll(btnFindPath, btnClearPath);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setTop(lblStatus);
        borderPane.setCenter(cellPane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(lblStatus, Pos.CENTER);

        Scene scene = new Scene(borderPane, 400, 420);
        primaryStage.setTitle("Ex_26");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnFindPath.setOnAction(event -> {
//            count = 0;
            // Clear previous path (if any) before finding new one.
            clearPath();

            // Pre-check possibility of the path
            if (cells[0][0].isChecked || cells[rowCount - 1][columnCount - 1].isChecked) {
                lblStatus.setText("Path cannot exist with this formation");
                return;
            }

            if (findPath(0, 0, 0)) {
                lblStatus.setText("Path found");
            } else {
                lblStatus.setText("No path found");
            }
//            System.out.println(count);
        });

        btnClearPath.setOnAction(event -> clearPath());
    }

    private void clearPath() {
        for(Cell[] row : cells)
            for (Cell cell: row)
                cell.setCrossed(false);
        lblStatus.setText("Set up and click Find Path");
    }

    private boolean findPath(int row, int column, int direction) {
        cells[row][column].setCrossed(true);
//        System.out.println(++count);
//        count++;

        // Base case, path found
        if (row == 8 && column == 8) {
            return true;
        }

        // Check for incorrect path, e.g. making a square
        // Check from top left
        if (row + 1 < rowCount && column + 1 < columnCount && cells[row + 1][column].isCrossed
                && cells[row][column + 1].isCrossed && cells[row + 1][column + 1].isCrossed) {
            cells[row][column].setCrossed(false);
            return false;
        }
        // Check from top right
        if (row + 1 < rowCount && column - 1 >= 0 && cells[row][column - 1].isCrossed
                && cells[row + 1 ][column].isCrossed && cells[row + 1][column - 1].isCrossed) {
            cells[row][column].setCrossed(false);
            return false;
        }
        // Check from bottom left
        if (row - 1 >= 0 && column + 1 < columnCount && cells[row - 1][column].isCrossed
                && cells[row - 1][column + 1].isCrossed && cells[row][column + 1].isCrossed) {
            cells[row][column].setCrossed(false);
            return false;
        }
        // Check from bottom right
        if (row - 1 >= 0 && column - 1 >= 0 && cells[row - 1][column].isCrossed
                && cells[row - 1][column - 1].isCrossed && cells[row][column - 1].isCrossed) {
            cells[row][column].setCrossed(false);
            return false;
        }

        boolean found = false;
        // Direction: Keep going with the current direction to avoid needless steps. E.g. Going straight until you're stuck.
        // direction's value: 0 for right, 1 for down, 2 for left and 3 for up

        // Go with current direction
        switch (direction) {
            case 0:
                if (column + 1 < columnCount && !cells[row][column + 1].isCrossed && !cells[row][column + 1].isChecked) {
                    found = findPath(row, column + 1, direction);
                }
                break;
            case 1:
                if (row + 1 < rowCount && !cells[row + 1][column].isCrossed && !cells[row + 1][column].isChecked) {
                    found = findPath(row + 1, column, 1);
                }
                break;
            case 3:
                if (row - 1 >= 0 && !cells[row - 1][column].isCrossed && !cells[row - 1][column].isChecked) {
                    found = findPath(row - 1, column, 3);
                }
                break;
            case 4:
                if (column - 1 >= 0 && !cells[row][column - 1].isCrossed && !cells[row][column - 1].isChecked) {
                    found = findPath(row, column - 1, 2);
                }
                break;
        }

        // Brute-force technique, trying all the possible ways to make a path
        // To the right cell
        if (!found && column + 1 < columnCount && !cells[row][column + 1].isCrossed && !cells[row][column + 1].isChecked && direction != 0) {
            found = findPath(row, column + 1, 0);
        }
        // To the below cell
        if (!found && row + 1 < rowCount && !cells[row + 1][column].isCrossed && !cells[row + 1][column].isChecked && direction != 1) {
            found = findPath(row + 1, column, 1);
        }
        // To the upper cell
        if (!found && row - 1 >= 0 && !cells[row - 1][column].isCrossed && !cells[row - 1][column].isChecked && direction != 3) {
            found = findPath(row - 1, column, 3);
        }
        // To the left cell
        if (!found && column - 1 >= 0 && !cells[row][column - 1].isCrossed && !cells[row][column - 1].isChecked && direction != 2) {
            found = findPath(row, column - 1, 2);
        }

        if (found) {
            return true;
        } else {
            cells[row][column].setCrossed(false);
            return false;
        }
    }

    private class Cell extends Pane {
        private boolean isChecked, isCrossed;
        private Group xShape = new Group();

        public Cell() {
            Line line1 = new Line(5, 5,
                    this.getWidth() - 5, this.getHeight() - 5);
            line1.endXProperty().bind(this.widthProperty().subtract(5));
            line1.endYProperty().bind(this.heightProperty().subtract(5));
            Line line2 = new Line(5, this.getHeight() - 5,
                    this.getWidth() - 5, 5);
            line2.startYProperty().bind(
                    this.heightProperty().subtract(5));
            line2.endXProperty().bind(this.widthProperty().subtract(5));
            xShape.getChildren().addAll(line1, line2);
            xShape.setVisible(isChecked);

            this.getChildren().add(xShape);
            this.setPrefSize(50, 50);
            this.setOnMouseClicked(event -> handleOnMouseClicked());
            this.setStyle("-fx-background-color: white");
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
            xShape.setVisible(isChecked);
        }

        public void setCrossed(boolean isCrossed) {
            this.isCrossed = isCrossed;
            if (isCrossed)
                this.setStyle("-fx-background-color: lightgray");
            else
                this.setStyle("-fx-background-color: white");
        }

        private void handleOnMouseClicked() {
            setChecked(!isChecked);
        }
    }
}
