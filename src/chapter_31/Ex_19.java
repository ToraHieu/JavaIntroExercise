package chapter_31;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ex_19 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane s1 = new StackPane(new Circle(40));
        StackPane s2 = new StackPane(new Rectangle(40, 60));
        StackPane s3 = new StackPane(new Arc(0, 0, 40, 30, 90, 90));
        StackPane s4 = new StackPane(new Ellipse(40, 30));
        SplitPane left = new SplitPane();
        left.setOrientation(Orientation.VERTICAL);
        left.getItems().addAll(s1, s2);
        SplitPane right = new SplitPane();
        right.getItems().addAll(s3, s4);
        right.setOrientation(Orientation.VERTICAL);

        SplitPane splitPane = new SplitPane(left, right);

        Scene scene = new Scene(splitPane, 400, 200);
        primaryStage.setTitle("Ex_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
