package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_03 extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        int[] chosen = new int[3];
        chosen[0] = (int) (Math.random() * 52 + 1);
        int i2 = (int) (Math.random() * 52 + 1);
        while (i2 == chosen[0]) 
            i2 = (int) (Math.random() * 52 + 1);
        chosen[1] = i2;
        int i3 = (int) (Math.random() * 52 + 1);
        while (i3 == chosen[0] || i3 == chosen[1]) 
            i3 = (int) (Math.random() * 52 + 1);
        chosen[2] = i3;

        Image img1 = new Image(getClass().getResource("/resources/card/" + chosen[0] + ".png").toURI().toString());
        Image img2 = new Image(getClass().getResource("/resources/card/" + chosen[1] + ".png").toURI().toString());
        Image img3 = new Image(getClass().getResource("/resources/card/" + chosen[2] + ".png").toURI().toString());
        
        HBox pane = new HBox();
        pane.getChildren().add(new ImageView(img1));
        pane.getChildren().add(new ImageView(img2));
        pane.getChildren().add(new ImageView(img3));
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Excercise14_03"); 
        primaryStage.setScene(scene); 
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
