package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_06 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        Text text1 = new Text("Java is fun");
        Text text2 = new Text("Java is powerful");
        
        pane.getChildren().add(text1);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_06");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.setOnMouseClicked(o -> {
            if (pane.getChildren().get(0).equals(text1)) {
                pane.getChildren().clear();
                pane.getChildren().add(text2);
            } else {
                pane.getChildren().clear();
                pane.getChildren().add(text1);
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
