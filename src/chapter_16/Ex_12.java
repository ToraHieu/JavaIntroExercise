package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_12 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ScrollPane paneForTextArea = new ScrollPane();
        TextArea textArea = new TextArea();
        paneForTextArea.setContent(textArea);
        paneForTextArea.setFitToHeight(true);
        paneForTextArea.setFitToWidth(true);

        CheckBox chkEditable = new CheckBox("Editable");
        chkEditable.setSelected(true);
        CheckBox chkWrap = new CheckBox("Wrap");
        chkWrap.setSelected(true);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(chkEditable, chkWrap);
        hBox.setAlignment(Pos.CENTER);

        chkEditable.setOnAction(e -> textArea.setEditable(chkEditable.isSelected()));
        chkWrap.setOnAction(e -> textArea.setWrapText(chkWrap.isSelected()));

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForTextArea);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Ex_12");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
