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

import java.util.Random;


public class Ex_16 extends Application {
    private static final int UNIT_HEIGHT = 10, COLUMN_WIDTH = 25, FONT_SIZE = 12;
    private static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private final Rectangle[] columns = new Rectangle[numbers.length];
    private final Text[] columnsValue = new Text[numbers.length];
    private final TextField inputTf = new TextField();
    private final Label status = new Label("Enter a key");
    private final Button stepBtn = new Button("Step"), resetBtn = new Button("Reset");
    private int comparingIndex = -1;
    private double key = -1;

    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < numbers.length; i++) {
            columns[i] = new Rectangle(COLUMN_WIDTH, (i + 1) * UNIT_HEIGHT);
            columns[i].setFill(null);
            columns[i].setStroke(Color.BLACK);
            columnsValue[i] = new Text(i + 1 + "");
            columnsValue[i].setFont(Font.font(FONT_SIZE));
        }

        MainPane centerPane = new MainPane();
        HBox hBox = new HBox(new Label("Key (in double)"), inputTf, stepBtn, resetBtn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        BorderPane pane = new BorderPane();
        pane.setTop(status);
        pane.setCenter(centerPane);
        pane.setBottom(hBox);

        BorderPane.setAlignment(status, Pos.CENTER);

        Scene scene = new Scene(pane, 650, 320);
        primaryStage.setTitle("Ex_16");
        primaryStage.setScene(scene);
        primaryStage.show();

        randomizeNumber();
        centerPane.drawPane();

        stepBtn.setOnMouseClicked(event -> {
            if (key == -1) {
                try {
                    key = Double.parseDouble(inputTf.getText());
                    inputTf.setEditable(false);
                } catch (Exception e) {
                    status.setText("Input must be a number");
                    return;
                }
            }
            status.setText("Finding the key");

            comparingIndex++;
            if (comparingIndex >= numbers.length) {
                status.setText("The key is not in the array");
                key = -1;
                stepBtn.setDisable(true);
                return;
            }
            centerPane.colorComparingColumn();
            centerPane.clearColorOfColumn(comparingIndex - 1);

            if (numbers[comparingIndex] == key) {
                status.setText("The key is found in the array at index " + comparingIndex);
                key = -1;
                stepBtn.setDisable(true);
            }
        });

        resetBtn.setOnMouseClicked(event -> {
            centerPane.clearColorOfColumn(comparingIndex);
            inputTf.setEditable(true);
            stepBtn.setDisable(false);
            comparingIndex = -1;
            randomizeNumber();
            centerPane.drawPane();
            status.setText("Enter a key");
        });
    }

    private void randomizeNumber() {
        Random rng = new Random();
        for (int i = 0; i < numbers.length; i++) {
            int newIndex = rng.nextInt(numbers.length);
            int temp = numbers[i];
            numbers[i] = numbers[newIndex];
            numbers[newIndex] = temp;
        }
    }

    private class MainPane extends Pane {
        public MainPane() {
            super();
            getChildren().addAll(columns);
            getChildren().addAll(columnsValue);
            setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
//            setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }

        void drawPane() {
            for (int index = 0; index < numbers.length; index++) {
                int position = numbers[index] - 1;
                columns[position].setX(12 + index * COLUMN_WIDTH);
                columns[position].setY(getHeight() - columns[position].getHeight());
                columnsValue[position].setX(index * COLUMN_WIDTH + 16);
                columnsValue[position].setY(columns[position].getY() - FONT_SIZE + 5);
            }
        }

        void colorComparingColumn() {
            columns[numbers[comparingIndex] - 1].setFill(Color.RED);
        }

        void clearColorOfColumn(int index) {
            if (index == numbers.length)
                columns[numbers[index - 1] - 1].setFill(null);
            else if (index >= 0)
                columns[numbers[index] - 1].setFill(null);
        }
    }
}
