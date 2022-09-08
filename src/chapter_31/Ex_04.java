package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Ex_04 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Random random = new Random();
        Text[] texts = new Text[5];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = new Text("Java");
            texts[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
            texts[i].setRotate(90);
            texts[i].setFill(new Color(random.nextDouble(), random.nextDouble(),
                    random.nextDouble(), random.nextDouble()));
        }
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(texts);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_04");
        primaryStage.show();
    }
}
