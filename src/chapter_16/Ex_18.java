package chapter_16;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_18 extends Application {
    @Override
    public void start(Stage primaryStage) {
        FanPane fanPane = new FanPane();

        HBox hBox = new HBox(5);
        Button pause = new Button("Pause");
        pause.setOnAction(event -> fanPane.pause());
        Button resume = new Button("Resume");
        resume.setOnAction(event -> fanPane.play());
        Button reverse = new Button("Reverse");
        reverse.setOnAction(event -> fanPane.reverse());
        hBox.getChildren().addAll(pause, resume, reverse);
        hBox.setAlignment(Pos.CENTER);


        Slider slider = new Slider(0, 2, 1);
        slider.setBlockIncrement(0.1);
        slider.setPadding(new Insets(5));
        slider.valueProperty().addListener(observable -> {
            fanPane.setSpeed(slider.getValue());
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(fanPane);
        borderPane.setBottom(slider);

        Scene scene = new Scene(borderPane, 250, 240);
        primaryStage.setTitle("Ex_18");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.widthProperty().addListener(e -> fanPane.setW(fanPane.getWidth()));
        scene.heightProperty().addListener(e -> fanPane.setH(fanPane.getHeight()));
    }
}

class FanPane extends Pane {
    private double w = 250;
    private double h = 200;
    private double radius = Math.min(w, h) * 0.45;
    private Circle circle = new Circle(w / 2, h / 2, radius);
    private Arc[] arcs = new Arc[4];
    private Group group = new Group();
    private RotateTransition rt;

    public FanPane() {
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        for (int i = 0; i < arcs.length; i++) {
            arcs[i] = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9, i * 90, 35);
            arcs[i].setFill(Color.ORANGE);
            arcs[i].setType(ArcType.ROUND);
        }
        group.getChildren().addAll(arcs);
        getChildren().addAll(circle, group);

        rt = new RotateTransition(Duration.millis(3000), group);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    public void play() {
        rt.play();
    }

    public void pause() {
        rt.pause();
    }

    public void reverse() {
        rt.setRate(-rt.getRate());
    }

    public void setSpeed(double speed) {
        rt.setRate(Math.signum(rt.getRate()) * speed);
    }

    private void setValues() {
        radius = Math.min(w, h) * 0.45;
        circle.setRadius(radius);
        circle.setCenterX(w / 2);
        circle.setCenterY(h / 2);

        for (int i = 0; i < 4; i++) {
            arcs[i].setRadiusX(radius * 0.9);
            arcs[i].setRadiusY(radius * 0.9);
            arcs[i].setCenterX(w / 2);
            arcs[i].setCenterY(h / 2);
        }
    }

    public void setW(double w) {
        this.w = w;
        setValues();
    }

    public void setH(double h) {
        this.h = h;
        setValues();
    }
}
