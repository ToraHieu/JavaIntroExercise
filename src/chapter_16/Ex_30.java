package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_30 extends Application {
    private static final int numberOfRows = 6, numberOfColumns = 7;
    private Text result;
    private GridPane gridPane;
    private Button btnSolve;

    @Override
    public void start(Stage primaryStage) {
        result = new Text("Place holder");

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                TextField textField = new TextField((int) (Math.random() * 10) + "");
                textField.setPrefColumnCount(1);
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-border-color: black");
                gridPane.add(textField, j, i);
            }
        }
        gridPane.setAlignment(Pos.CENTER);

        btnSolve = new Button("Solve");
        btnSolve.setOnAction(event -> findConsecutiveFour());

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(result);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(btnSolve);
        BorderPane.setAlignment(result, Pos.CENTER);
        BorderPane.setAlignment(btnSolve, Pos.CENTER);
        BorderPane.setMargin(gridPane, new Insets(5, 10, 5, 10));

        Scene scene = new Scene(borderPane, 250, 250);
        primaryStage.setTitle("Ex_30");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void findConsecutiveFour() {
        for (Node node: gridPane.getChildren()) {
            node.setStyle("-fx-border-color: black");
        }

        for (Node node: gridPane.getChildren()) {
            int rowIndex = GridPane.getRowIndex(node);
            int columnIndex = GridPane.getColumnIndex(node);

            // Reserved for highlighting the node
            TextField[] textFields;

            // Search horizontally
            if (columnIndex < numberOfColumns - 3) {
                textFields = new TextField[4];
                textFields[0] = (TextField) node;
                for (int i = 1; i < 4; i++) {
                    TextField tf = (TextField) (gridPane.getChildren().get(rowIndex * numberOfColumns + columnIndex + i));
                    if (tf.getText().equals(textFields[0].getText())) {
                        textFields[i] = tf;
                    } else {
                        textFields = null;
                        break;
                    }
                }
                if (textFields != null) {
                    foundConsecutiveFour(textFields);
                    return;
                }
            }

            // Search vertically
            if (rowIndex < numberOfRows - 3) {
                textFields = new TextField[4];
                textFields[0] = (TextField) node;
                for (int i = 1; i < 4; i++) {
                    TextField tf = (TextField) (gridPane.getChildren().get((rowIndex + i) * numberOfColumns + columnIndex));
                    if (tf.getText().equals(textFields[0].getText())) {
                        textFields[i] = tf;
                    } else {
                        textFields = null;
                        break;
                    }
                }
                if (textFields != null) {
                    foundConsecutiveFour(textFields);
                    return;
                }
            }

            // Search major diagonally (\)
            if (rowIndex < numberOfRows - 3 && columnIndex < numberOfColumns - 3) {
                textFields = new TextField[4];
                textFields[0] = (TextField) node;
                for (int i = 1; i < 4; i++) {
                    TextField tf = (TextField) (gridPane.getChildren().get((rowIndex + i) * numberOfColumns + (columnIndex + i)));
                    if (tf.getText().equals(textFields[0].getText())) {
                        textFields[i] = tf;
                    } else {
                        textFields = null;
                        break;
                    }
                }
                if (textFields != null) {
                    foundConsecutiveFour(textFields);
                    return;
                }
            }

            // Search minor diagonally (/)
            if (rowIndex >= numberOfRows - 3 && columnIndex < numberOfColumns - 3) {
                textFields = new TextField[4];
                textFields[0] = (TextField) node;
                for (int i = 1; i < 4; i++) {
                    TextField tf = (TextField) (gridPane.getChildren().get((rowIndex - i) * numberOfColumns + (columnIndex + i)));
                    if (tf.getText().equals(textFields[0].getText())) {
                        textFields[i] = tf;
                    } else {
                        textFields = null;
                        break;
                    }
                }
                if (textFields != null) {
                    foundConsecutiveFour(textFields);
                    return;
                }
            }
        }
        // Completed search, nothing found.
        result.setText("No consecutive four found");
    }

    private void foundConsecutiveFour(TextField[] textFields) {
        for (TextField textField: textFields)
            textField.setStyle("-fx-border-color: red");
        result.setText("A consecutive four found");
    }
}
