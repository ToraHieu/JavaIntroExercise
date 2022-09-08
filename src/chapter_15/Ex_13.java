package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_13 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_14");
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle rectangle = new Rectangle(100, 60, 100, 40);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Text text = new Text("");

        pane.getChildren().addAll(rectangle, text);

        scene.setOnMouseMoved(event -> {
             if (rectangle.contains(event.getX(), event.getY())) {
                 text.setText("Mouse point is inside the rectangle");
             } else {
                 text.setText("Mouse point is outside the rectangle");
             }
             text.setX(event.getX());
             text.setY(event.getY());
        });
    }
}
