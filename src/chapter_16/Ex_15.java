package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex_15 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> cboContentDisplay = new ComboBox<>();
        cboContentDisplay.getItems().addAll("LEFT", "RIGHT", "CENTER", "TOP", "BOTTOM");
        cboContentDisplay.setValue("LEFT");
        TextField tfTextGap = new TextField("10");
        tfTextGap.setPrefColumnCount(3);

        HBox topPane = new HBox(5);
        topPane.getChildren().addAll(new Label("contentDisplay:"), cboContentDisplay, new Label("graphicTextGap:"), tfTextGap);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(5));

        Label label = new Label("Grapes");
        label.setContentDisplay(ContentDisplay.LEFT);
        label.setGraphic(new ImageView("image/grapes.gif"));
        label.setGraphicTextGap(10);

        cboContentDisplay.setOnAction(e -> label.setContentDisplay(ContentDisplay.valueOf(cboContentDisplay.getValue())));
        tfTextGap.setOnAction(e -> label.setGraphicTextGap(Integer.parseInt(tfTextGap.getText())));

        BorderPane pane = new BorderPane();
        pane.setTop(topPane);
        pane.setCenter(label);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
