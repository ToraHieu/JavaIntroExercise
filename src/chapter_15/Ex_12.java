package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_12 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 600, 150);
        primaryStage.setTitle("Ex_12");
        primaryStage.setScene(scene);
        primaryStage.show();

        Circle circle = new Circle(100, 60, 50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        Text text = new Text("");

        pane.getChildren().addAll(circle, text);

        scene.setOnMouseMoved(event -> {
            double distance = Math.sqrt(
                    Math.abs((event.getX() - circle.getCenterX()) * (event.getX() - circle.getCenterX()))
                    + Math.abs((event.getY() - circle.getCenterY()) * (event.getY() - circle.getCenterY())));
            if (distance < circle.getRadius()) {
                text.setText("Mouse point is inside the circle");
            } else {
                text.setText("Mouse point is outside the circle");
            }
            text.setX(event.getX());
            text.setY(event.getY());
        });
    }
}
