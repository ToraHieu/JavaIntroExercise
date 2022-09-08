package chapter_16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex_10 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ScrollPane paneForTextArea = new ScrollPane();
        TextArea textArea = new TextArea();
        paneForTextArea.setContent(textArea);

        HBox paneForControls = new HBox();
        Label lblFileName = new Label("Filename");
        TextField tfFilePath = new TextField();
        lblFileName.setGraphic(tfFilePath);
        lblFileName.setContentDisplay(ContentDisplay.RIGHT);
        Button btView = new Button("View");
        paneForControls.getChildren().addAll(lblFileName, btView);

        btView.setOnAction(event -> {
            File file = new File(tfFilePath.getText().trim());
            if (file.exists()) {
                try {
                    Scanner input = new Scanner(file);
                    while (input.hasNextLine()) {
                        textArea.appendText(input.nextLine() + "\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                textArea.setText("File doesn't exist.");
            }
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForTextArea);
        pane.setBottom(paneForControls);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
