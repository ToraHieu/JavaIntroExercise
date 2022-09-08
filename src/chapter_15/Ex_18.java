package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ex_18 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle rectangle = new Rectangle(40, 40, 40, 40);
        rectangle.setFill(Color.BLUE);

        pane.setOnMouseDragged(event -> {
            if (rectangle.contains(event.getX(), event.getY())) {
                rectangle.setX(event.getX() - rectangle.getWidth() / 2);
                rectangle.setY(event.getY() - rectangle.getHeight() / 2);
            }
        });
        pane.getChildren().add(rectangle);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
