package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_20 extends Application {
    private byte second = 0;
    private byte minute = 0;
    private byte hour = 0;
    private boolean isRunning = false;
    private Text stopwatch = new Text("00:00:00");
    private Button action = new Button("Start");
    private Button clear = new Button("Clear");
    private Timeline animation;

    @Override
    public void start(Stage primaryStage) {
        stopwatch.setFont(Font.font(42));

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(action, clear);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(stopwatch);
        BorderPane.setAlignment(stopwatch, Pos.CENTER);
        borderPane.setBottom(hBox);

        EventHandler<ActionEvent> eventHandler = event -> updateStopWatch();
        animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);

        Scene scene = new Scene(borderPane, 250, 100);
        primaryStage.setTitle("Ex_20");
        primaryStage.setScene(scene);
        primaryStage.show();

        action.setOnAction(event -> handlingAction());
        clear.setOnAction(event -> resetStopWatch());
    }

    private void resetStopWatch() {
        second = 0;
        minute = 0;
        hour = 0;
        stopwatch.setText("00:00:00");
        isRunning = false;
        action.setText("Start");
        animation.stop();
    }

    private void updateStopWatch() {
        second++;

        if (second == 60) {
            minute += 1;
            second = 0;
        }

        if (minute == 60) {
            hour += 1;
            minute = 0;
        }
        String time = String.format("%02d:%02d:%02d", hour, minute, second);
        stopwatch.setText(time);
    }

    private void handlingAction() {
        if (isRunning) {
            animation.pause();
            action.setText("Resume");
        } else {
            animation.play();
            action.setText("Pause");
        }
        isRunning = !isRunning;
    }
}
