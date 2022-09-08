package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        Image us = new Image(getClass().getResource("/resources/us.jpg").toURI().toString());
        Image uk = new Image(getClass().getResource("/resources/uk.jpg").toURI().toString());
        Image cn = new Image(getClass().getResource("/resources/china.jpg").toURI().toString());
        Image cd = new Image(getClass().getResource("/resources/canada.jpg").toURI().toString());
        
        pane.add(new ImageView(uk), 0, 0);
        pane.add(new ImageView(cd), 0, 1);
        pane.add(new ImageView(cn), 1, 0);
        pane.add(new ImageView(us), 1, 1);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Excercise14_01"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
