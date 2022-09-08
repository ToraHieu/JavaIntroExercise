package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_14 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> cboFontName = new ComboBox<>();
        cboFontName.getItems().addAll(Font.getFamilies());
        Label lblFontName = new Label("Font Name");
        lblFontName.setGraphic(cboFontName);
        lblFontName.setContentDisplay(ContentDisplay.RIGHT);

        ComboBox<String> cboFontSize = new ComboBox<>();
        for (int i = 1; i < 101; i++) cboFontSize.getItems().add("" + i);
        Label lblFontSize = new Label("Font Size");
        lblFontSize.setGraphic(cboFontSize);
        lblFontSize.setContentDisplay(ContentDisplay.RIGHT);

        Text text = new Text("Programming is fun");

        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");

        HBox topPane = new HBox(5);
        topPane.getChildren().addAll(lblFontName, lblFontSize);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(5));

        HBox bottomPane = new HBox(5);
        bottomPane.getChildren().addAll(chkBold, chkItalic);
        bottomPane.setAlignment(Pos.CENTER);

        cboFontName.setOnAction(event ->
            text.setFont(Font.font(cboFontName.getValue(),
                    chkBold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    chkItalic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    Integer.parseInt(cboFontSize.getValue())))
        );

        cboFontSize.setOnAction(event ->
            text.setFont(Font.font(cboFontName.getValue(),
                    chkBold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    chkItalic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    Integer.parseInt(cboFontSize.getValue())))
        );

        chkBold.setOnAction(event ->
            text.setFont(Font.font(cboFontName.getValue(),
                    chkBold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    chkItalic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    Integer.parseInt(cboFontSize.getValue())))
        );

        chkItalic.setOnAction(event ->
            text.setFont(Font.font(cboFontName.getValue(),
                    chkBold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    chkItalic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    Integer.parseInt(cboFontSize.getValue())))
        );

        BorderPane pane = new BorderPane();
        pane.setTop(topPane);
        pane.setCenter(text);
        pane.setBottom(bottomPane);

        Scene scene = new Scene(pane, 530, 250);
        primaryStage.setTitle("Ex_14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
