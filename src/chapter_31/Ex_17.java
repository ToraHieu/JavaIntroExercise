package chapter_31;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_17 extends Application {
    private TextField amountTf;
    private TextField yearTf;
    private TextField rateTf;
    private TextField valueTf;

    @Override
    public void start(Stage primaryStage) {
        amountTf = new TextField();
        amountTf.setAlignment(Pos.CENTER_RIGHT);
        yearTf = new TextField();
        yearTf.setAlignment(Pos.CENTER_RIGHT);
        rateTf = new TextField();
        rateTf.setAlignment(Pos.CENTER_RIGHT);
        valueTf = new TextField();
        valueTf.setAlignment(Pos.CENTER_RIGHT);
        valueTf.setEditable(false);

        Button calculateBtn = new Button("Calculate");
        calculateBtn.setOnAction(e -> calculate());

        MenuItem calculateItem = new MenuItem("Calculate");
        calculateItem.setOnAction(event -> calculate());
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));
        Menu operation = new Menu("Operation");
        operation.getItems().addAll(calculateItem, exitItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(operation);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.addColumn(0, new Text("Investment Amount:"), new Text("Numbers of Years:"),
                new Text("Annual Interest Rate:"), new Text("Future value:"));
        gridPane.addColumn(1, amountTf, yearTf, rateTf, valueTf, calculateBtn);
        gridPane.setHgap(5);
        gridPane.setVgap(2);
        GridPane.setHalignment(calculateBtn, HPos.RIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Ex_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate() {
        double amount = Double.parseDouble(amountTf.getText());
        int years = Integer.parseInt(yearTf.getText());
        double rate = Double.parseDouble(rateTf.getText()) / 1200; // year => month => %
        double value = amount * Math.pow(1 + rate, years * 12);
        valueTf.setText("$" + String.format("%.2f", value));
    }
}
