package chapter_15;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_23 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        Polygon polygon = new Polygon();
        polygon.setFill(Color.RED);
        ObservableList<Double> list = polygon.getPoints();

        final double WIDTH = 200, HEIGHT = 200;
        double centerX = WIDTH / 2, centerY = HEIGHT / 2;
        double radius = Math.min(WIDTH, HEIGHT) * 0.4;

        // Add points to the polygon list
        for (int i = 0; i < 6; i++) {
            list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
            list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
        }

        Text text = new Text("STOP");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setFill(Color.WHITE);

        pane.getChildren().addAll(polygon, text);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Ex_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
