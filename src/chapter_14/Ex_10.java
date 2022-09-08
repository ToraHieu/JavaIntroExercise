package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_10 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Ellipse ellipse = new Ellipse(70, 50, 50, 20);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        
        Line line1 = new Line(20, 50, 20, 150);
        Line line2 = new Line(120, 50, 120, 150);
        
        Arc arc1 = new Arc(70, 150, 50, 20, 0, -180);
        arc1.setFill(null);
        arc1.setStroke(Color.BLACK);
        
        Arc arc2 = new Arc(70, 150, 50, 20, 0, 180);
        arc2.setFill(null);
        arc2.setStroke(Color.BLACK);
        arc2.getStrokeDashArray().addAll(6.0, 21.0);
        
        pane.getChildren().addAll(ellipse, line1, line2, arc1, arc2);
        
        Scene scene = new Scene(pane, 140, 200);
        primaryStage.setTitle("Ex_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
