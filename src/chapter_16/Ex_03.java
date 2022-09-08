package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_03 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circleRed = new Circle(10);
        circleRed.setFill(null);
        circleRed.setStroke(Color.BLACK);
        Circle circleYellow = new Circle(10);
        circleYellow.setFill(null);
        circleYellow.setStroke(Color.BLACK);
        Circle circleGreen = new Circle(10);
        circleGreen.setFill(null);
        circleGreen.setStroke(Color.BLACK);

        HBox paneForCircles = new HBox();
        paneForCircles.getChildren().addAll(circleRed, circleYellow, circleGreen);
        paneForCircles.setAlignment(Pos.CENTER);
        paneForCircles.setSpacing(10);
        paneForCircles.setStyle("-fx-border-color: black");
        paneForCircles.setMaxSize(90, 30);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbGreen.setToggleGroup(group);

        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                circleRed.setFill(Color.RED);
                circleYellow.setFill(null);
                circleGreen.setFill(null);
            }
        });
        rbYellow.setOnAction(e -> {
            if (rbYellow.isSelected()) {
                circleYellow.setFill(Color.YELLOW);
                circleRed.setFill(null);
                circleGreen.setFill(null);
            }
        });
        rbGreen.setOnAction(e -> {
            if (rbGreen.isSelected()) {
                circleGreen.setFill(Color.GREEN);
                circleRed.setFill(null);
                circleYellow.setFill(null);
            }
        });

        HBox paneForRadioButtons = new HBox();
        paneForRadioButtons.getChildren().addAll(rbRed, rbYellow, rbGreen);
        paneForRadioButtons.setAlignment(Pos.CENTER);
        paneForRadioButtons.setSpacing(5);

        BorderPane pane = new BorderPane();
        pane.setBottom(paneForRadioButtons);
        pane.setCenter(paneForCircles);
        
        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setTitle("Ex_03");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
