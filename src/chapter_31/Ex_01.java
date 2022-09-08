package chapter_31;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 50, 0, 50));
        Scene scene = new Scene(hBox, 450, 150);
        scene.getStylesheets().add("chapter_31/mystyle.css");
        
        Circle c1 = new Circle(50);
        c1.getStyleClass().add("plaincircle");
        StackPane p1 = new StackPane(c1);
        p1.getStyleClass().add("border");

        Circle c2 = new Circle(50);
        c2.getStyleClass().add("plaincircle");
        StackPane p2 = new StackPane(c2);

        Circle c3 = new Circle(50);
        c3.setId("greencircle");
        StackPane p3 = new StackPane(c3);

        Circle c4 = new Circle(50);
        StackPane p4 = new StackPane(c4);
        p4.getStyleClass().add("border");

        hBox.getChildren().addAll(p1, p2, p3, p4);

        primaryStage.setTitle("Ex_01"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }
}
