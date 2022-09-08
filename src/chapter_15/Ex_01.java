package chapter_15;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    private ArrayList<Integer> cards;
    private HBox hBox = new HBox();
    
    @Override
    public void start(Stage primaryStage) throws URISyntaxException {
        cards = new ArrayList<>();
        for (int i = 0; i < 52;) {
            cards.add(++i);
        }
        getCards();
        
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            try {
                getCards();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        
        BorderPane pane = new BorderPane();
        pane.setCenter(hBox);
        pane.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void getCards() throws URISyntaxException {
        java.util.Collections.shuffle(cards);
        hBox.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(
                    getClass().getResource("/resources/card/" + cards.get(i) + ".png").toURI().toString());
            hBox.getChildren().add(img);
        }
    }
}
