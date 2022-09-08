package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_21 extends Application {
    Timeline animation;
    Time time = new Time();
    TextField textField = new TextField("00");

    @Override
    public void start(Stage primaryStage) {
        Media sound = new Media("https://www.soundjay.com/clock/sounds/alarm-clock-01.mp3");
        MediaPlayer player = new MediaPlayer(sound);

        textField.setPrefColumnCount(4);
        textField.setFont(Font.font(40));
        textField.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane();
        pane.getChildren().add(textField);

        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setTitle("Ex_21");
        primaryStage.setScene(scene);
        primaryStage.show();

        animation = new Timeline(new KeyFrame(Duration.millis(1000), event ->  {
            time.decrease();
            textField.setText("" + time.getValue());
            if (time.getValue() == 0) {
                animation.stop();
                player.play();
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);

        textField.setOnAction(event -> {
            time.setValue(Integer.parseInt(textField.getText()));
            animation.play();
        });
    }

    static class Time {
        int value = 0;

        void decrease() {
            if (value != 0) value--;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }
}
