package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_05 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        double x = 100;
        double y = 100;
        
        String s = "WELCOME TO JAVA ";
        double radius = 50;
        for (int i = 0; i < s.length(); i++) {
            double endX = x + radius *
                    Math.sin(i * (2 * Math.PI / s.length()));
            double endY = y - radius *
                    Math.cos(i * (2 * Math.PI / s.length()));
            Text text = new Text(endX, endY, s.charAt(i) + "");
            text.setRotate(i * 360 / s.length());
            pane.getChildren().add(text);
        }
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
