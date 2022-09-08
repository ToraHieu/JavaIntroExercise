package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ex_14 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ImageView image = new ImageView("image/stop_sign.png");
        StackPane pane = new StackPane(image);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Ex_14");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    image.setScaleX(image.getScaleX() + 0.1);
                    image.setScaleY(image.getScaleY() + 0.1);
                    break;
                case DOWN:
                    image.setScaleX(image.getScaleX() - 0.1);
                    image.setScaleY(image.getScaleY() - 0.1);
                    break;
                case LEFT:
                    image.setRotate(image.getRotate() - 45);
                    break;
                case RIGHT:
                    image.setRotate(image.getRotate() + 45);
                    break;
            }
        });
    }
}
