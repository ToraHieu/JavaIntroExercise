package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_07 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        Circle circle = new Circle(50, 50, 40);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        
        pane.getChildren().add(circle);
        
        Scene scene = new Scene(pane, 100, 100);
        primaryStage.setTitle("Ex_07");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        circle.setOnMousePressed(o -> circle.setFill(Color.BLACK));
        circle.setOnMouseClicked(o -> circle.setFill(Color.WHITE));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}