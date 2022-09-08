package chapter_22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** Programming Exercise 22.18 (Binary search animation)
 * Write a program that animates the binary search algorithm.
 * Create an array with NUMBERS from 1 to 25 in this order.
 * The array elements are displayed in a histogram, as shown in Figure 22.13.
 * You need to enter a search key in the text field.
 * Clicking the Step button causes the program to perform one comparison in the algorithm.
 * Use a light-gray color to paint the bars for the NUMBERS in the current search range
 * and use a black color to paint the bar indicating the middle number in the search range.
 * The Step button also freezes the text field to prevent its value from being changed.
 * When the algorithm is finished, display the status in a label at the top of a border pane.
 * Clicking the Reset button enables a new search to start. This button also makes the text field editable.
 *
 * Bonus: Extra number of steps information displayed in the status label when the search finished.
 * */
public class Ex_18 extends Application {
    private static final int UNIT_HEIGHT = 10, COLUMN_WIDTH = 25, FONT_SIZE = 12, NUMBER_OF_NUMBER = 25;
    private static final int[] NUMBERS = new int[NUMBER_OF_NUMBER];
    private final Rectangle[] columns = new Rectangle[NUMBER_OF_NUMBER];
    private final Text[] columnsValue = new Text[NUMBER_OF_NUMBER];
    private final TextField inputTf = new TextField();
    private final Label status = new Label("Enter a key");
    private final Button stepBtn = new Button("Step"), resetBtn = new Button("Reset");
    private int currentIndex = -1, stepCount = 0, low, high;
    private Double key = null;
    private MainPane centerPane;

    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < NUMBER_OF_NUMBER; i++) {
            NUMBERS[i] = i + 1;
            columns[i] = new Rectangle(COLUMN_WIDTH, (i + 1) * UNIT_HEIGHT);
            columns[i].setFill(null);
            columns[i].setStroke(Color.BLACK);
            columnsValue[i] = new Text(i + 1 + "");
            columnsValue[i].setFont(Font.font(FONT_SIZE));
        }

        centerPane = new MainPane();
        HBox hBox = new HBox(new Label("Key (in double)"), inputTf, stepBtn, resetBtn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        BorderPane pane = new BorderPane();
        pane.setTop(status);
        pane.setCenter(centerPane);
        pane.setBottom(hBox);

        BorderPane.setAlignment(status, Pos.CENTER);

        Scene scene = new Scene(pane, calculateWidth(), calculateHeight());
        primaryStage.setTitle("Ex_18");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        centerPane.drawPane();

        stepBtn.setOnMouseClicked(event -> {
            if (key == null) {
                try {
                    key = Double.parseDouble(inputTf.getText().trim());
                    inputTf.setEditable(false);
                    status.setText("Finding the key");
                    low = 0;
                    high = NUMBER_OF_NUMBER - 1;
                } catch (Exception e) {
                    status.setText("Input must be an integer");
                    return;
                }
            }

            stepCount++;

            currentIndex = (low + high + 1) / 2;
            centerPane.colorComparingColumns();
            centerPane.clearColorOfColumns();

            if (NUMBERS[currentIndex] == key) {
                finishSearching(true);
                return;
            } else {
                if (NUMBERS[currentIndex] < key) {
                    low = currentIndex + 1;
                } else {
                    high = currentIndex - 1;
                }
            }

            if (currentIndex == low || currentIndex == high) {
                finishSearching(false);
            }
        });

        resetBtn.setOnMouseClicked(event -> reset());
    }

    private void finishSearching(boolean found) {
        stepBtn.setDisable(true);
        if (found) {
            status.setText("The key is found in the array at index " + currentIndex + " at step #" + stepCount);
        } else {
            status.setText("The key is not in the array after " + stepCount + " step" + (stepCount != 1 ? "s" : ""));
        }
        stepCount = 0;
        key = null;
    }

    private void reset() {
        inputTf.setEditable(true);
        stepBtn.setDisable(false);
        key = null;
        currentIndex = -1;

        centerPane.clearColorOfColumns();
        status.setText("Enter a key");
    }

    private double calculateWidth() {
        return COLUMN_WIDTH * NUMBER_OF_NUMBER + 20;
    }

    private double calculateHeight() {
        return UNIT_HEIGHT * NUMBER_OF_NUMBER + 70;
    }

    private class MainPane extends Pane {
        private MainPane() {
            super();
            getChildren().addAll(columns);
            getChildren().addAll(columnsValue);
            setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        void drawPane() {
            for (int index = 0; index < NUMBER_OF_NUMBER; index++) {
                int position = NUMBERS[index] - 1;
                columns[position].setX(10 + index * COLUMN_WIDTH);
                columns[position].setY(getHeight() - columns[position].getHeight());
                columnsValue[position].setX(index * COLUMN_WIDTH + 16);
                columnsValue[position].setY(columns[position].getY() - FONT_SIZE + 5);
            }
        }

        void colorComparingColumns() {
            for (int i = low; i < currentIndex; i++)
                columns[i].setFill(Color.LIGHTYELLOW);
            columns[currentIndex].setFill(Color.RED);
            for (int i = currentIndex + 1; i <= high; i++)
                columns[i].setFill(Color.LIGHTYELLOW);
        }

        void clearColorOfColumns() {
            if (currentIndex == -1) {
                for (int i = 0; i < NUMBER_OF_NUMBER; i++) {
                    columns[i].setFill(null);
                }
                return;
            }
            for (int i = 0; i < low; i++) {
                columns[i].setFill(null);
            }
            for (int i = high + 1; i < NUMBER_OF_NUMBER; i++) {
                columns[i].setFill(null);
            }
        }
    }
}
