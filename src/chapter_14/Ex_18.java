package chapter_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_18 extends Application {
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        Line xAxis = new Line(10, 160, 390, 160);
        Line yAxis = new Line(200, 20, 200, 200);
        
        Text xText = new Text(390, 150, "x");
        Text yText = new Text(220, 20, "y");
        
        // Draw arrows
        Line line3 = new Line(390, 160, 370, 150);
        Line line4 = new Line(390, 160, 370, 170);
        Line line5 = new Line(200, 20, 190, 50);
        Line line6 = new Line(200, 20, 210, 50);
        
        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 0.0125;
        for (int x = -100; x <= 100; x++) {
            list.add( x + 200.0);
            list.add(scaleFactor * -(x * x) + 160);
        }
        
        pane.getChildren().addAll(xAxis, yAxis, xText, yText, polyline, line3, line4, line5, line6);
        
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
