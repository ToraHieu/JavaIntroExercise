package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex_02 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 20, 5, 20));
        pane.setHgap(5);
        pane.setVgap(5);
        
        Image x = new Image(getClass().getResource("/resources/x.gif").toURI().toString());
        Image o = new Image(getClass().getResource("/resources/o.gif").toURI().toString());
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int random = (int) (Math.random() * 3);
                switch (random) {
                case 0: 
                    pane.add(new ImageView(x), i, j);
                    break;
                case 1:
                    pane.add(new ImageView(o), i, j);
                }
            }
        }
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Excercise14_02"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
