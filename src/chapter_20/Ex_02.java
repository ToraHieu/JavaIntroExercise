package chapter_20;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;

public class Ex_02 extends Application {
    private LinkedList<Integer> list = new LinkedList<>();
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        TextField tfInput = new TextField();
        tfInput.setPromptText("An integer only");
        HBox topBox = new HBox(5);
        topBox.getChildren().addAll(new Label("Enter a number: "), tfInput);
        topBox.setAlignment(Pos.CENTER);

        textArea = new TextArea();
        textArea.setWrapText(true);

        Button btnSort = new Button("Sort");
        Button btnShuffle = new Button("Shuffle");
        Button btnReverse = new Button("Reverse");
        HBox bottomBox = new HBox(10);
        bottomBox.getChildren().addAll(btnSort, btnShuffle, btnReverse);
        bottomBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(topBox);
        pane.setCenter(textArea);
        pane.setBottom(bottomBox);

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_02");
        primaryStage.setScene(scene);
        primaryStage.show();

        tfInput.setOnAction(event -> {
            if (tfInput.getText().matches("-?\\d+")) {
                list.addLast(Integer.parseInt(tfInput.getText()));
                displayNumbers();
                tfInput.clear();
            }
        });

        btnSort.setOnAction(event -> {
            Collections.sort(list);
            displayNumbers();
        });

        btnShuffle.setOnAction(event -> {
            Collections.shuffle(list);
            displayNumbers();
        });

        btnReverse.setOnAction(event -> {
            Collections.reverse(list);
            displayNumbers();
        });
    }

    private void displayNumbers() {
        textArea.clear();
        list.forEach(e -> textArea.appendText(e + " "));
    }
}
