package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_14 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_14");
        primaryStage.setScene(scene);
        primaryStage.show();

        Polygon polygon = new Polygon(40, 20, 70, 40, 60, 80, 45, 45, 20, 60);
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);

        Text text = new Text("");

        pane.getChildren().addAll(polygon, text);

        scene.setOnMouseMoved(event -> {
            if (polygon.contains(event.getX(), event.getY())) {
                text.setText("Mouse point is inside the polygon");
            } else {
                text.setText("Mouse point is outside the polygon");
            }
            text.setX(event.getX());
            text.setY(event.getY());
        });
    }
}
