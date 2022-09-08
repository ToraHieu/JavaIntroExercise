package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_17 extends Application {
    Text text = new Text("Show Colors");
    ScrollBar red = new ScrollBar();
    ScrollBar green = new ScrollBar();
    ScrollBar blue = new ScrollBar();
    ScrollBar opacity = new ScrollBar();

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        opacity.setValue(100);

        red.valueProperty().addListener(ov -> {
            text.setFill(new Color(red.getValue() / 100, green.getValue() / 100, blue.getValue() / 100, opacity.getValue() / 100));
        });

        green.valueProperty().addListener(ov -> {
            text.setFill(new Color(red.getValue() / 100, green.getValue() / 100, blue.getValue() / 100, opacity.getValue() / 100));
        });

        blue.valueProperty().addListener(ov -> {
            text.setFill(new Color(red.getValue() / 100, green.getValue() / 100, blue.getValue() / 100, opacity.getValue() / 100));
        });

        opacity.valueProperty().addListener(ov -> {
            text.setFill(new Color(red.getValue() / 100, green.getValue() / 100, blue.getValue() / 100, opacity.getValue() / 100));
        });

        GridPane gridPane = new GridPane();
        gridPane.addRow(0, new Text("Red"), red);
        gridPane.addRow(1, new Text("Green"), green);
        gridPane.addRow(2, new Text("Blue"), blue);
        gridPane.addRow(3, new Text("Opacity"), opacity);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        pane.setCenter(text);
        pane.setBottom(gridPane);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane, 350, 150);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
