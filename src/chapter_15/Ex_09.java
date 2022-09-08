package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_09 extends Application {
    private double currentX;
    private double currentY;

    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_09");
        primaryStage.setScene(scene);
        primaryStage.show();

        currentX = pane.getWidth() / 2;
        currentY = pane.getHeight() / 2;

        scene.setOnKeyPressed(e -> {
            Line line = new Line(currentX, currentY, currentX, currentY);
            switch (e.getCode()) {
            case UP: {
                currentY -= 10;
                line.setEndY(currentY);
                break;
            }
            case DOWN: {
                currentY += 10;
                line.setEndY(currentY);
                break;
            }
            case LEFT: {
                currentX -= 10;
                line.setEndX(currentX);
                break;
            }
            case RIGHT:
                currentX += 10;
                line.setEndX(currentX);
                break;
            default: 
            }
            pane.getChildren().add(line);
        });
    }

}
