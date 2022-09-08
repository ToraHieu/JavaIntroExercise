package chapter_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_19 extends Application {
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        Line xAxis = new Line(10, 110, 390, 110);
        Line yAxis = new Line(200, 20, 200, 200);
        
        Text xText = new Text(390, 100, "X");
        Text yText = new Text(220, 20, "Y");
        
        Text t0 = new Text(200, 120, "0");
        Text t1 = new Text(200 - 50, 120, "-\u03c0");
        Text t2 = new Text(200 - 100, 120, "-2\u03c0");
        Text t3 = new Text(200 + 50, 120, "\u03c0");
        Text t4 = new Text(200 + 100, 120, "2\u03c0");
        
        // Draw arrows
        Line line3 = new Line(390, 110, 370, 100);
        Line line4 = new Line(390, 110, 370, 120);
        Line line5 = new Line(200, 20, 190, 50);
        Line line6 = new Line(200, 20, 210, 50);
        
        double scaleFactor = 50;
        Polyline polyline1 = new Polyline();
        polyline1.setStroke(Color.RED);
        ObservableList<Double> list = polyline1.getPoints();
        for (int x = -170; x <= 170; x++) {
            list.add(x + 200.0);
            list.add(110 - scaleFactor * Math.sin((x / 100.0) * 2 * Math.PI));
        }
        Polyline polyline2 = new Polyline();
        polyline2.setStroke(Color.BLUE);
        ObservableList<Double> list2 = polyline2.getPoints();
        for (int x = -170; x <= 170; x++) {
            list2.add(x + 200.0);
            list2.add(110 - scaleFactor * Math.cos((x / 100.0) * 2 * Math.PI));
        }
        
        pane.getChildren().addAll(xAxis, yAxis, xText, yText, polyline1, polyline2, 
                line3, line4, line5, line6, t0, t1, t2, t3, t4);
        
        Scene scene = new Scene(pane, 400, 220);
        primaryStage.setTitle("Ex_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
