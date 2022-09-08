package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Ex_16 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ellipse top = new Ellipse(200, 50, 40, 20);
        top.setFill(null);
        top.setStroke(Color.BLACK);
        Line l1 = new Line(160, 50, 160, 150);
        Line l2 = new Line(240, 50, 240, 150);
        Arc botVisible = new Arc(200, 150, 40, 20, 180, 180);
        botVisible.setType(ArcType.OPEN);
        botVisible.setFill(null);
        botVisible.setStroke(Color.BLACK);
        Arc botInvisible = new Arc(200, 150, 40, 20, 0, 180);
        botInvisible.setType(ArcType.OPEN);
        botInvisible.setFill(null);
        botInvisible.setStroke(Color.BLACK);
        botInvisible.getStrokeDashArray().add(10d);

        Pane pane = new Pane(top, l1, l2, botVisible, botInvisible);
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
