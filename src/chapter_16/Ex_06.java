package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_06 extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField("JavaFX");

        HBox paneForTextField = new HBox();
        paneForTextField.getChildren().addAll(new Label("Text Field"), textField);
        paneForTextField.setAlignment(Pos.CENTER);
        paneForTextField.setSpacing(5);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbLeft = new RadioButton("Left");
        RadioButton rbCenter = new RadioButton("Center");
        RadioButton rbRight = new RadioButton("Right");
        rbLeft.setToggleGroup(group);
        rbCenter.setToggleGroup(group);
        rbRight.setToggleGroup(group);
        TextField tfColumnSize = new TextField("" + textField.getPrefColumnCount());
        tfColumnSize.setPrefColumnCount(4);
        tfColumnSize.setAlignment(Pos.BOTTOM_RIGHT);

        HBox paneForProperties = new HBox();
        paneForProperties.getChildren().addAll(rbLeft, rbCenter, rbRight, new Label("Column Size"), tfColumnSize);
        paneForProperties.setAlignment(Pos.CENTER);
        paneForProperties.setSpacing(5);
        paneForProperties.setPadding(new Insets(5));

        rbLeft.setOnAction(e -> {
            textField.setAlignment(Pos.CENTER_LEFT);
        });
        rbCenter.setOnAction(e -> {
            textField.setAlignment(Pos.CENTER);
        });
        rbRight.setOnAction(e -> {
            textField.setAlignment(Pos.CENTER_RIGHT);
        });
        tfColumnSize.setOnAction(e -> {
            textField.setPrefColumnCount(Integer.parseInt(tfColumnSize.getText()));
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForTextField);
        pane.setBottom(paneForProperties);

        Scene scene = new Scene(pane, 400, 70);
        primaryStage.setTitle("Ex_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
