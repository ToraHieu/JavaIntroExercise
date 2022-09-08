package chapter_36;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.TimeZone;

public class Ex_04 extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();

        Button btnzones = new Button("All zones");
        Button btnTimeZones = new Button("All Time Zones");
        HBox hBox = new HBox(btnzones, btnTimeZones);
        hBox.setSpacing(5);

        BorderPane pane = new BorderPane(textArea);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_04");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnzones.setOnAction(e -> {
            textArea.clear();
            Locale[] zones = Locale.getAvailableLocales();
            if (zones.length > 0) {
                textArea.appendText(zones[0].getDisplayName() + " " + zones[0]);
                for (int i = 1; i < zones.length; i++) {
                    textArea.appendText("\n" + zones[i].getDisplayName() + " " + zones[i]);
                }
            }
        });

        btnTimeZones.setOnAction(e -> {
            textArea.clear();
            String[] zones = TimeZone.getAvailableIDs();
            if (zones.length > 0) {
                textArea.appendText(zones[0]);
                for (int i = 1; i < zones.length; i++) {
                    textArea.appendText("\n" + zones[i]);
                }
            }
        });
    }
}
