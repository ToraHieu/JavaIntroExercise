package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_07 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Rectangle rectangle = new Rectangle(50,40, null);
        rectangle.setStroke(Color.BLACK);
        rectangle.setX(40);
        rectangle.setY(40);

        Pane pane = new Pane(rectangle);

        TextField xTf = new TextField();
        xTf.setPrefColumnCount(2);
        TextField yTf = new TextField();
        yTf.setPrefColumnCount(2);
        Button translateBtn = new Button("Translate");
        HBox hBox = new HBox(2);
        hBox.getChildren().addAll(new Text("x:"), xTf, new Text("y:"), yTf, translateBtn);
        hBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(hBox);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Ex_07");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        translateBtn.setOnMouseClicked(event -> {
            double x = Double.parseDouble(xTf.getText());
            double y = Double.parseDouble(yTf.getText());
            rectangle.setTranslateX(x);
            rectangle.setTranslateY(y);
        });
    }
}
