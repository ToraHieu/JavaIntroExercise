package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_09 extends Application {
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(5, 5, 5, 5));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Circle circle = new Circle();
                circle.setRadius(50);
                circle.setStroke(Color.BLACK);
                circle.setFill(null);
                pane.add(circle, i, j);
                Pane p = new Pane();
                for (int k = 0; k < 4; k++) {
                    Arc arc = new Arc(50, 50, 40, 40, k * 2 * (360 / 8), 30);
                    arc.setType(ArcType.ROUND);
                    p.getChildren().add(arc);
                }
                pane.add(p, i, j);
            }
        }
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_09");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
