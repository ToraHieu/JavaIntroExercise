package chapter_15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ex_02 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(20, 20, 100, 40);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        stackPane.getChildren().add(rectangle);
        
        Button button = new Button("Rotate");
        button.setOnAction(e -> rectangle.setRotate(rectangle.getRotate() + 15));
        
        BorderPane pane = new BorderPane();
        pane.setCenter(stackPane);
        pane.setBottom(button);
        BorderPane.setAlignment(button, Pos.TOP_CENTER);
        
        Scene scene = new Scene(pane, 200, 150);
        primaryStage.setTitle("Ex_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
