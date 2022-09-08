package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_09 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ellipse ellipse = new Ellipse(60, 40);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        StackPane pane = new StackPane(ellipse);

        TextField xTf = new TextField();
        xTf.setPrefColumnCount(2);
        TextField yTf = new TextField();
        yTf.setPrefColumnCount(2);
        Button scaleBtn = new Button("Scale");
        HBox hBox = new HBox(2);
        hBox.getChildren().addAll(new Text("Scale factor for x:"), xTf, new Text("y:"), yTf, scaleBtn);
        hBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(hBox);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Ex_09");
        primaryStage.setScene(scene);
        primaryStage.show();

        scaleBtn.setOnMouseClicked(event -> {
            double x = Double.parseDouble(xTf.getText());
            double y = Double.parseDouble(yTf.getText());
            ellipse.setScaleX(x);
            ellipse.setScaleY(y);
        });
    }
}
