package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_08 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ellipse ellipse = new Ellipse(60, 40);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        StackPane pane = new StackPane(ellipse);

        TextField angleTf = new TextField();
        angleTf.setPrefColumnCount(2);
        Button rotateBtn = new Button("Rotate");
        HBox hBox = new HBox(2);
        hBox.getChildren().addAll(new Text("Angle:"), angleTf, rotateBtn);
        hBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(hBox);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Ex_08");
        primaryStage.setScene(scene);
        primaryStage.show();

        rotateBtn.setOnMouseClicked(event -> {
            double theta = Double.parseDouble(angleTf.getText());
            ellipse.setRotate(theta);
        });
    }
}
