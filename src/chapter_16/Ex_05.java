package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex_05 extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);

        TextField tfDecimal = new TextField();
        tfDecimal.setAlignment(Pos.CENTER_RIGHT);
        TextField tfHex = new TextField();
        tfHex.setAlignment(Pos.CENTER_RIGHT);
        TextField tfBinary = new TextField();
        tfBinary.setAlignment(Pos.CENTER_RIGHT);

        tfDecimal.setOnAction(e -> {
            int decimal = Integer.parseInt(tfDecimal.getText());
            tfHex.setText(Integer.toHexString(decimal));
            tfBinary.setText(Integer.toBinaryString(decimal));
        });

        tfHex.setOnAction(e -> {
            int decimal = Integer.parseInt(tfHex.getText(), 16);
            tfDecimal.setText("" + decimal);
            tfBinary.setText(Integer.toBinaryString(decimal));
        });

        tfBinary.setOnAction(e -> {
            int decimal = Integer.parseInt(tfBinary.getText(), 2);
            tfDecimal.setText("" + decimal);
            tfHex.setText(Integer.toHexString(decimal));
        });

        pane.addRow(0, new Label("Decimal"), tfDecimal);
        pane.addRow(1, new Label("Hex"), tfHex);
        pane.addRow(2, new Label("Binary"), tfBinary);

        Scene scene = new Scene(pane, 250, 120);
        primaryStage.setTitle("Ex_05");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
