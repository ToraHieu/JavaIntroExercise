package chapter_36;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Locale;

public class Ex_03 extends Application {
    private CalendarPane calendarPane = new CalendarPane();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        pane.setCenter(calendarPane);
        calendarPane.setLocale(Locale.getDefault());

        HBox root = new HBox(pane, new WorldClock());

        // Create a scene and place it in the stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("CalendarApp"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
