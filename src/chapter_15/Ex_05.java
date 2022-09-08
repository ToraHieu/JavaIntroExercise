package chapter_15;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex_05 extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);
        
        TextField tfInvest = new TextField();
        TextField tfYears = new TextField();
        TextField tfRate = new TextField();
        TextField tfFuture = new TextField();
        
        pane.addRow(0, new Label("Investment Amount"), tfInvest);
        pane.addRow(1, new Label("Number of Years"), tfYears);
        pane.addRow(2, new Label("Annual Interest Rate"), tfRate);
        pane.addRow(3, new Label("Future value"), tfFuture);
        
        Button btCalcutate = new Button("Calculate");
        pane.add(btCalcutate, 1, 4);
        GridPane.setHalignment(btCalcutate, HPos.RIGHT);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_05");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        btCalcutate.setOnAction(e -> tfFuture.setText("$" + (
                Double.parseDouble(tfInvest.getText()) 
                * Math.pow(1 + Double.parseDouble(tfRate.getText()) / 100 / 12, Integer.parseInt(tfYears.getText()) * 12 ))));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
