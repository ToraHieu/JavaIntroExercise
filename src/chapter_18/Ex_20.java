package chapter_18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_20 extends Application {
    @Override
    public void start(Stage primaryStage) {
        OvalsPane pane = new OvalsPane();
        pane.heightProperty().addListener(observable -> pane.drawPane());
        pane.widthProperty().addListener(observable -> pane.drawPane());

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class OvalsPane extends StackPane {
        public void drawPane() {
            getChildren().clear();
            drawOvals(10);
        }

        private void drawOvals(int radius) {
            if (radius >= getHeight() / 2 - 10 || radius >= getWidth() / 2 - 10)
                return;
            Circle circle = new Circle(radius);
            circle.setStroke(Color.BLACK);
            circle.setFill(null);
            getChildren().add(circle);
            drawOvals(radius + 10);
        }
    }
}
