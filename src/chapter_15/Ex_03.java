package chapter_15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_03 extends Application {
    @Override
    public void start(Stage primaryStage) {
        MovingBallPane ballPane = new MovingBallPane();
        
        HBox hBox = new HBox();
        
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");

        leftButton.setOnAction(e -> ballPane.moveLeft());
        rightButton.setOnAction(e -> ballPane.moveRight());
        upButton.setOnAction(e -> ballPane.moveUp());
        downButton.setOnAction(e -> ballPane.moveDown());

        hBox.getChildren().addAll(leftButton, rightButton, upButton, downButton);
        hBox.setAlignment(Pos.CENTER);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(hBox);
        BorderPane.setAlignment(ballPane, Pos.CENTER);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);
        
        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setTitle("Ex_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
