package chapter_16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_23 extends Application {
    int currentImageIndex = 1;

    @Override
    public void start(Stage primaryStage) {
        Button startAnimation = new Button("Start Animation");

        ImageView imageView = new ImageView(new Image("image/L1.gif"));

        TextField tfAnimationSpeed = new TextField();
        TextField tfFilePrefix = new TextField();
        TextField tfNumberImage = new TextField();
        TextField tfAudioURL = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Enter information for animation"), 0, 0);
        gridPane.addRow(1, new Text("Animation speed in milliseconds"), tfAnimationSpeed);
        gridPane.addRow(2, new Text("Image file prefix"), tfFilePrefix);
        gridPane.addRow(3, new Text("Number of images"), tfNumberImage);
        gridPane.addRow(4, new Text("Audio file URL"), tfAudioURL);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(startAnimation);
        borderPane.setCenter(imageView);
        borderPane.setBottom(gridPane);

        BorderPane.setAlignment(startAnimation, Pos.CENTER_RIGHT);

        Scene scene = new Scene(borderPane, 500, 700);
        primaryStage.setTitle("Ex_23");
        primaryStage.setScene(scene);
        primaryStage.show();

        tfAnimationSpeed.prefColumnCountProperty().bind(scene.widthProperty());

        startAnimation.setOnAction(event -> {
            int speed = Integer.parseInt(tfAnimationSpeed.getText());
            String imageURL = "image/" + tfFilePrefix.getText();
            int imageCount = Integer.parseInt(tfNumberImage.getText());
            AudioClip audio = new AudioClip(tfAudioURL.getText());
            audio.setCycleCount(AudioClip.INDEFINITE);

            Timeline animation = new Timeline(new KeyFrame(Duration.millis(speed), e -> {
                imageView.setImage(new Image(imageURL + currentImageIndex + ".gif"));
                currentImageIndex++;
            }));
            animation.setCycleCount(imageCount);
            animation.setOnFinished(e -> {
                audio.stop();
                currentImageIndex = 1;
            });
            animation.play();
            audio.play();
        });
    }


}
