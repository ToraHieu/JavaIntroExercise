package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_10 extends Application {
    private String s = "";

    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();

        Text text = new Text();
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_10");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.ENTER) {
                s+= event.getText();
            } else {
                text.setText(s);
                s = "";
            }
        });
    }
}
