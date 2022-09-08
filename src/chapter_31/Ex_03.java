package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Ex_03 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Random random = new Random();
        int[] cards = new int[52];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = i;
        }
        for (int i = 0; i < cards.length - 1; i++) {
            int j = random.nextInt(52);
            int temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        ImageView card0 = new ImageView("image/card/" + cards[0] + ".png");
        ImageView card1 = new ImageView("image/card/" + cards[1] + ".png");
        ImageView card2 = new ImageView("image/card/" + cards[2] + ".png");

        HBox pane = new HBox();
        pane.getChildren().addAll(card0, card1, card2);
        pane.setAlignment(Pos.CENTER);
        pane.getStyleClass().add("border");

        Scene scene = new Scene(pane, 250, 150);
        scene.getStylesheets().add("chapter_31/mystyle.css");
        primaryStage.setTitle("Ex_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
