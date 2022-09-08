package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Undone */
public class Ex_11 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox paneForControls = new HBox();
        Label lblFileName = new Label("Filename");
        TextField tfFilePath = new TextField();
        lblFileName.setGraphic(tfFilePath);
        lblFileName.setContentDisplay(ContentDisplay.RIGHT);
        Button btView = new Button("View");
        paneForControls.getChildren().addAll(lblFileName, btView);
        paneForControls.setStyle("-fx-border-color: black");

        btView.setOnAction(event -> {
            File file = new File(tfFilePath.getText().trim());
            if (file.exists()) {
                try {
                    Scanner input = new Scanner(file);
                    while (input.hasNextLine()) {

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {

            }
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(new Histogram());
        pane.setBottom(paneForControls);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class Histogram extends Pane {
        private int[] counts = new int[26];

        public Histogram() {
            super();
            HBox hBox = new HBox();
            for (int i = 0; i < counts.length; i++) {
                VBox vBox = new VBox();
                vBox.setAlignment(Pos.BOTTOM_CENTER);

            }
        }

        /** This method takes an array of 26 elements as parameter.
         * It will return true if the array consists of 26 elements and false otherwise. */
        public boolean setCounts(int[] counts) {
            if (counts.length != 26)
                return false;
            this.counts = counts;
            drawPane();
            return true;
        }

        private void drawPane() {

        }

        @Override
        protected void setHeight(double value) {
            super.setHeight(value);
            drawPane();
        }

        @Override
        protected void setWidth(double value) {
            super.setWidth(value);
            drawPane();
        }
    }
}
