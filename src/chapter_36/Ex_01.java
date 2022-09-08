package chapter_36;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        textField.setPrefColumnCount(4);

        textArea.setWrapText(false);
        textArea.setPrefColumnCount(25);
        textArea.setPrefRowCount(10);

        HBox hBox = new HBox(new Label("Specify a Unicode: "), textField);
        hBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(hBox);
        pane.setCenter(textArea);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        textField.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                textArea.clear();
                try {
                    int code = Integer.parseInt(textField.getText(), 16);
                    for (int i = 0; i < 20; i++) {
                        textArea.appendText(String.format("%04x", code));
                        for (int j = 0; j < 16; j++) {
                            textArea.appendText(" ");
                            textArea.appendText(Character.toString((char) code++));
                        }
                        textArea.appendText("\n");
                    }
                } catch (NumberFormatException e) {
                    textArea.setText("Not a valid Unicode");
                }
            }
        });
    }
}
