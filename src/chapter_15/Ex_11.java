package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_11 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Circle circle = new Circle(0, 0, 50);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        pane.getChildren().add(circle);
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();

        circle.setCenterX(pane.getWidth() / 2);
        circle.setCenterY(pane.getHeight() / 2);

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case UP: {
                    circle.setCenterY(circle.getCenterY() - 5);
                    break;
                }
                case DOWN:  {
                    circle.setCenterY(circle.getCenterY() + 5);
                    break;
                }
                case LEFT: {
                    circle.setCenterX(circle.getCenterX() - 5);
                    break;
                }
                case RIGHT: {
                    circle.setCenterX(circle.getCenterX() + 5);
                    break;
                }
            }
        });
    }
}
