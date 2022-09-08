package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Ex_22 extends Application {
    @Override
    public void start(Stage primaryStage) {
        AudioClip audioClip = new AudioClip("https://www.soundjay.com/button/sounds/button-3.mp3");

        Button play = new Button("Play");
        Button loop = new Button("Loop");
        Button stop = new Button("Stop");

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(play, loop, stop);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox, 200, 60);
        primaryStage.setTitle("Ex_22");
        primaryStage.setScene(scene);
        primaryStage.show();

        play.setOnAction(event -> {
            audioClip.stop();
            audioClip.setCycleCount(1);
            audioClip.play();
        });

        loop.setOnAction(event -> {

            audioClip.setCycleCount(AudioClip.INDEFINITE);
            audioClip.play();
        });

        stop.setOnAction(event -> {
            audioClip.stop();
        });
    }
}
