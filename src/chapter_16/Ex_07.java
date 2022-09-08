package chapter_16;

import chapter_14.ClockPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ex_07 extends Application {
    private ClockPane clockPane = new ClockPane();
    private TextField tfHour = new TextField();
    private TextField tfMinute = new TextField();
    private TextField tfSecond = new TextField();

    private static final int paneHeight = 220;
    private static final int paneWidth = 350;

    @Override
    public void start(Stage primaryStage) {
        StackPane paneForClock = new StackPane(clockPane);
        clockPane.setHeight(paneHeight / 2);
        clockPane.setWidth(paneWidth / 2);

        HBox paneForControls = new HBox();
        paneForControls.getChildren().addAll(new Label("Hour"), tfHour,
                new Label("Minute"), tfMinute, new Label("Second"), tfSecond);
        paneForControls.setSpacing(5);
        paneForControls.setAlignment(Pos.CENTER);
        paneForControls.setPadding(new Insets(5));

        tfHour.setAlignment(Pos.CENTER_RIGHT);
        tfHour.setPrefColumnCount(3);
        tfMinute.setAlignment(Pos.CENTER_RIGHT);
        tfMinute.setPrefColumnCount(3);
        tfSecond.setAlignment(Pos.CENTER_RIGHT);
        tfSecond.setPrefColumnCount(3);

        tfHour.setOnAction(e -> eventHandler());
        tfMinute.setOnAction(e -> eventHandler());
        tfSecond.setOnAction(e -> eventHandler());

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForClock);
        pane.setBottom(paneForControls);

        Scene scene = new Scene(pane, paneWidth, paneHeight + 30);
        primaryStage.setTitle("Ex_07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void eventHandler() {
        clockPane.setHour(Integer.parseInt(tfHour.getText()));
        clockPane.setMinute(Integer.parseInt(tfMinute.getText()));
        clockPane.setSecond(Integer.parseInt(tfSecond.getText()));
    }
}
