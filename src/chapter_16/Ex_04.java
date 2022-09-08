package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex_04 extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);

        TextField tfCelsius = new TextField();
        tfCelsius.setAlignment(Pos.CENTER_RIGHT);
        TextField tfFahrenheit = new TextField();
        tfFahrenheit.setAlignment(Pos.CENTER_RIGHT);

        tfCelsius.setOnAction(e -> {
            double celsius = Double.parseDouble(tfCelsius.getText());
            tfFahrenheit.setText("" + (celsius * 1.8 + 32));
        });

        tfFahrenheit.setOnAction(e -> {
            double fahrenheit = Double.parseDouble(tfCelsius.getText());
            tfCelsius.setText("" + ((fahrenheit - 32) / 1.8));
        });

        pane.add(new Label("Celsius"), 0, 0);
        pane.add(tfCelsius, 1, 0);
        pane.add(new Label("Fahrenheit"), 0, 1);
        pane.add(tfFahrenheit, 1, 1);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_04");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
