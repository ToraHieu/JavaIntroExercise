package chapter_15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_24 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(20, 100, 10, Color.ORANGE);
        QuadCurve arc = new QuadCurve(20, 100, 100, 140, 180, 100);
        arc.setStroke(Color.BLACK);
        arc.setFill(null);

        Pane pane = new Pane(arc, circle);
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Ex_24");
        primaryStage.setScene(scene);
        primaryStage.show();

        PathTransition pathTransition = new PathTransition(Duration.millis(1500), arc, circle);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();

        pane.setOnMousePressed(e -> pathTransition.pause());
        pane.setOnMouseReleased(e -> pathTransition.play());
    }
}