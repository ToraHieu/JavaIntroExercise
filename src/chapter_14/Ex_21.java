package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_21 extends Application {
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        double paneWidth = 300; 
        double paneHeight = 250;
        double x1 = Math.random() * (paneWidth - 12);
        double y1 = Math.random() * (paneHeight - 12);
        double x2 = Math.random() * (paneWidth - 12);
        double y2 = Math.random() * (paneHeight - 12);
        
        Line line = new Line(x1, y1, x2, y2);
        
        Circle c1 = new Circle(x1, y1, 15);
        Circle c2 = new Circle(x2, y2, 15);
        
        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        Text text = new Text((x1 + x2)/2, (y1 + y2)/ 2, distance + "");
        pane.getChildren().addAll(c1, c2, line, text);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Ex_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

