package chapter_21;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex_11 extends Application {
    private static final int START_YEAR = 2001;
    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage primaryStage) throws FileNotFoundException {
        Map<String, Integer>[] boysName = (HashMap<String, Integer>[]) new HashMap[10],
                girlsName = (HashMap<String, Integer>[]) new HashMap[10];
        // Get the data
        for (int i = 0; i < 10; i++) {
            boysName[i] = new HashMap<>();
            girlsName[i] = new HashMap<>();
            try (Scanner scanner = new Scanner(new File("babynamesranking" + (START_YEAR + i) +".txt"))) {
                for (int rank = 1; rank <= 1000; rank++) {
                    // Go the the boy name location
                    scanner.next(); scanner.skip("\\s"); // Skip the rank and blank space
                    // Put the boy name and its rank in
                    boysName[i].put(scanner.next(), rank);
                    // Go the the girl name location
                    scanner.skip("\\s"); scanner.next(); scanner.skip("\\s");// Skip the number of boy name and blank space
                    // Put the girl name and its rank in
                    girlsName[i].put(scanner.next(), rank);
                    // Go the next line
                    scanner.nextLine();
                }
            }
        }

        ComboBox<Integer> cbYear = new ComboBox<>();
        cbYear.getItems().addAll(2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010);
        cbYear.getSelectionModel().selectFirst();

        ComboBox<String> cbSex = new ComboBox<>();
        cbSex.getItems().addAll("Male", "Female");
        cbSex.getSelectionModel().selectFirst();

        TextField tfName = new TextField();
        Button btFindRanking = new Button("Find Ranking");

        GridPane centerPane = new GridPane();
        centerPane.addRow(0, new Label("Select a year:"), cbYear);
        centerPane.addRow(1, new Label("Boy or girl?"), cbSex);
        centerPane.addRow(2, new Label("Enter a name:"), tfName);
        centerPane.add(btFindRanking, 1, 3);
        centerPane.setVgap(4);
        centerPane.setHgap(4);
        centerPane.setPadding(new Insets(5));
        centerPane.setAlignment(Pos.CENTER);

        Label lblResult = new Label("Click Find Ranking");

        BorderPane pane = new BorderPane();
        pane.setCenter(centerPane);
        pane.setBottom(lblResult);
        BorderPane.setAlignment(lblResult, Pos.CENTER);

        Scene scene = new Scene(pane, 300, 150);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();

        btFindRanking.setOnAction(event -> {
            Map<String, Integer>[] targetName;
            String chosenSex;
            if (cbSex.getValue().equals("Male")) {
                targetName = boysName;
                chosenSex = "Boy";
            } else {
                targetName = girlsName;
                chosenSex = "Girl";
            }
            int chosenYear = cbYear.getValue();
            String chosenName = tfName.getText().trim();
            lblResult.setText(chosenSex + " name " + chosenName + " is ranked #" + targetName[chosenYear - START_YEAR].get(chosenName) + " in year " + chosenYear);
        });
    }
}
