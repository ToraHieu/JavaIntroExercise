package chapter_15;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_28 extends Application {
    private static final int centerX = 125;
    private static final int centerY = 90;

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(centerX, centerY, 80);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Arc arc1 = new Arc(centerX, centerY, 70, 70, 0, 40);
        arc1.setType(ArcType.ROUND);
        arc1.setFill(Color.ORANGE);

        Arc arc2 = new Arc(centerX, centerY, 70, 70, 60, 40);
        arc2.setType(ArcType.ROUND);

        Arc arc3 = new Arc(centerX, centerY, 70, 70, 120, 40);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.ORANGE);

        Arc arc4 = new Arc(centerX, centerY, 70, 70, 180, 40);
        arc4.setType(ArcType.ROUND);

        Arc arc5 = new Arc(centerX, centerY, 70, 70, 240, 40);
        arc5.setType(ArcType.ROUND);
        arc5.setFill(Color.ORANGE);

        Arc arc6 = new Arc(centerX, centerY, 70, 70, 300, 40);
        arc6.setType(ArcType.ROUND);

        Group group = new Group();
        group.getChildren().addAll(arc1, arc2, arc3, arc4, arc5, arc6);

        RotateTransition rt = new RotateTransition(Duration.millis(3000), group);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();

        Pane paneForFan = new Pane();
        paneForFan.getChildren().addAll(circle, group);

        HBox hBox = new HBox(5);
        Button pause = new Button("Pause");
        pause.setOnAction(event -> rt.pause());
        Button resume = new Button("Resume");
        resume.setOnAction(event -> rt.play());
        Button reverse = new Button("Reverse");
        reverse.setOnAction(event -> rt.setRate(-rt.getRate()));
        hBox.getChildren().addAll(pause, resume, reverse);
        hBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForFan);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("Ex_28");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
