package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_11 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a polyline to draw a sine curve
        Polyline polyline = new Polyline();
        for (double x = 0.1; x <= 102.5; x+= 0.1) {
            polyline.getPoints().addAll(
                    x * 4, Math.log(x) * -30);
        }
        polyline.setTranslateX(20);
        polyline.setTranslateY(200);
        polyline.setStrokeType(StrokeType.INSIDE);

        // Draw x-axis
        Line line1 = new Line(10, 250, 420, 250);
        Line line2 = new Line(420, 250, 400, 240);
        Line line3 = new Line(420, 250, 400, 260);

        // Draw y-axis
        Line line4 = new Line(20, 10, 20, 280);
        Line line5 = new Line(20, 10, 10, 30);
        Line line6 = new Line(20, 10, 30, 30);

        // Draw x, y axis labels
        Text text1 = new Text(410, 230, "X");
        Text text2 = new Text(30, 20, "Y");

        // Add nodes to a pane
        Pane pane = new Pane();
        pane.getChildren().addAll(polyline, line1, line2, line3, line4,
                line5, line6, text1, text2);

        Scene scene = new Scene(pane, 450, 300);
        primaryStage.setTitle("Ex_11"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }
}
