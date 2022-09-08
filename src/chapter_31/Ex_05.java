package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Ex_05 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(40);
        circle.setCenterX(100);
        circle.setCenterY(50);

        Path path = new Path();
        path.getElements().add(new MoveTo(100, 10));
        path.getElements().add(new CubicCurveTo(60, 30, 140, 70, 100, 90));
        path.getElements().add(new CubicCurveTo(60, 70, 140, 30, 100, 10));
        path.getElements().add(new ClosePath());
        path.setFill(Color.WHITE);
        path.setStroke(null);

        Pane pane = new Pane(circle, path);
        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setTitle("Ex_05");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
