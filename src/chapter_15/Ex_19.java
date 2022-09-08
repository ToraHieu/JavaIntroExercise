package chapter_15;

import chapter_09.StopWatch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_19 extends Application {
    private int circleCount = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_19");
        primaryStage.setScene(scene);
        primaryStage.show();

        StopWatch watch = new StopWatch();
        watch.start();

        Circle circle = new Circle(10);
        circle.setCenterX(Math.random() * (pane.getWidth() - circle.getRadius()) + circle.getRadius());
        circle.setCenterY(Math.random() * (pane.getHeight() - circle.getRadius()) + circle.getRadius());
        circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));

        pane.getChildren().add(circle);
        circle.setOnMouseClicked(event -> {
            circleCount++;
            if (circleCount < 20) {
                circle.setCenterX(Math.random() * (pane.getWidth() - circle.getRadius()));
                circle.setCenterY(Math.random() * (pane.getHeight() - circle.getRadius()));
                circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
            } else {
                watch.stop();
                pane.getChildren().remove(circle);
                Text text = new Text(20, 20,"Time spent is " + watch.getElapsedTime() +  " milliseconds");
                pane.getChildren().add(text);
            }
        });
    }
}
