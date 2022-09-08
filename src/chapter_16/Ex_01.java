package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane paneForText = new Pane();
        Text text = new Text(20, 20, "Welcome to Java");
        paneForText.getChildren().add(text);
        paneForText.setStyle("-fx-border-color: blue ");

        Button btnLeft = new Button("<=");
        Button btnRight = new Button("=>");
        HBox paneForButtons = new HBox();
        paneForButtons.getChildren().addAll(btnLeft, btnRight);
        paneForButtons.setAlignment(Pos.CENTER);

        HBox paneForRadioButtons = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton rbRed = new RadioButton("Red ");
        RadioButton rbYellow = new RadioButton("Yellow ");
        RadioButton rbBlack = new RadioButton("Black ");
        RadioButton rbOrange = new RadioButton("Orange ");
        RadioButton rbGreen = new RadioButton("Green ");
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbBlack.setToggleGroup(group);
        rbOrange.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        paneForRadioButtons.getChildren().addAll(rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
        paneForRadioButtons.setAlignment(Pos.CENTER);

        rbRed.setOnAction(e -> text.setFill(Color.RED));
        rbYellow.setOnAction(e -> text.setFill(Color.YELLOW));
        rbBlack.setOnAction(e -> text.setFill(Color.BLACK));
        rbOrange.setOnAction(e -> text.setFill(Color.ORANGE));
        rbGreen.setOnAction(e -> text.setFill(Color.GREEN));
        btnLeft.setOnAction(event -> text.setX(text.getX() - 10));
        btnRight.setOnAction(event -> text.setX(text.getX() + 10));

        BorderPane pane = new BorderPane();
        pane.setTop(paneForRadioButtons);
        pane.setCenter(paneForText);
        pane.setBottom(paneForButtons);

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
