package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_13 extends Application {
    @SuppressWarnings("SpellCheckingInspection")
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Polyline n2 = new Polyline();
        for (double x = 0.00; x <= 16; x+= 0.04) {
            n2.getPoints().addAll(
                    x * 25, x * x * -25);
        }
        n2.setTranslateX(20);
        n2.setTranslateY(250);
        n2.setStroke(Color.BLUE);

        Polyline n = new Polyline();
        for (double x = 0.00; x <= 16; x+= 0.04) {
            n.getPoints().addAll(
                    x * 25, x * -25);
        }
        n.setTranslateX(20);
        n.setTranslateY(250);
        n.setStroke(Color.GREEN);

        Polyline logn = new Polyline();
        for (double x = 0.04; x <= 16; x+= 0.04) {
            logn.getPoints().addAll(
                    x * 25, Math.log(x) * -25);
        }
        logn.setTranslateX(20);
        logn.setTranslateY(250);
        logn.setStroke(Color.RED);

        Polyline nlogn = new Polyline();
        for (double x = 0.04; x <= 16; x+= 0.04) {
            nlogn.getPoints().addAll(
                    x * 25, x * Math.log(x) * -25);
        }
        nlogn.setTranslateX(20);
        nlogn.setTranslateY(250);
        nlogn.setStroke(Color.ORANGE);

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
        pane.getChildren().addAll(n2, logn, nlogn, n,
                line1, line2, line3, line4,
                line5, line6, text1, text2);

        Scene scene = new Scene(pane, 450, 300);
        primaryStage.setTitle("Ex_13"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }
}
